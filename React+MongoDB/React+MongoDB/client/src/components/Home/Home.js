import React, { useState, useEffect } from 'react';
import { Container, Grow, Paper } from '@material-ui/core';
import { useDispatch } from 'react-redux';
import { useLocation } from 'react-router-dom';

import { getPosts } from '../../actions/posts';
import Posts from '../Posts/Posts';
import Form from '../Form/Form';
import Pagination from '../Pagination';
import useStyles from './styles';

function useQuery() {
  return new URLSearchParams(useLocation().search);
}

const Home = () => {
  const classes = useStyles();
  const query = useQuery();
  const page = query.get('page') || 1;

  const [currentId, setCurrentId] = useState(0);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getPosts());
  }, [currentId, dispatch]);

  return (
    <Grow in>
      <Container maxWidth="md" container justify="space-between" alignItems="stretch" spacing={3} >

        <Form currentId={currentId} setCurrentId={setCurrentId} />
        <Posts setCurrentId={setCurrentId} />

        <Paper className={classes.pagination} elevation={6}>
          <Pagination page={page} />
        </Paper>

      </Container>
    </Grow>
  );
};

export default Home;
