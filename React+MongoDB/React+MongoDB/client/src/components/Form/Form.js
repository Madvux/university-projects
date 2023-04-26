import React, { useState, useEffect } from 'react';
import { TextField, Button, Typography, Paper} from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import FileBase from 'react-file-base64';
import { useHistory } from 'react-router-dom';
import Alert from '@material-ui/lab/Alert';


import { createPost, updatePost } from '../../actions/posts';
import useStyles from './styles';

const Form = ({ currentId, setCurrentId }) => {
  const [postData, setPostData] = useState({ title: '', message: '', selectedFile: '' });
  const [formErrors, setFormErrors] = useState({ titleError: '', messageError: '' });
  const post = useSelector((state) => (currentId ? state.posts.posts.find((message) => message._id === currentId) : null));
  const dispatch = useDispatch();
  const classes = useStyles();
  const user = JSON.parse(localStorage.getItem('profile'));
  const history = useHistory();

  const clear = () => {
    setCurrentId(0);
    setPostData({ title: '', message: '', selectedFile: '' });
  };

  useEffect(() => {
    if (!post?.title) clear();
    if (post) setPostData(post);
  }, [post]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const regex_title = /^[\w\s]{2,40}$/;
    const regex_message = /^[\s\S]{0,550}$/;
    var error = false;
    if (!(postData.title) || !(regex_title.test(postData.title))) {
      setFormErrors({ titleError: "Tytuł jest wymagany, 2-40 znaków bez znaków specjalnych" })
      error = true;
    }
    else {
      setFormErrors({ titleError: '' });
      error = false;
    }
    if (!regex_message.test(postData.message)) {
      setFormErrors({ messageError: "Opis może mieć max 550 znaków - ze spacjami" })
      error = true;
    }

    if (error === false) {
      if (currentId === 0) {
        dispatch(createPost({ ...postData, name: user?.result?.name }, history));
        clear();
      } else {
        dispatch(updatePost(currentId, { ...postData, name: user?.result?.name }));
        clear();
      }
    }

  };

  if (!user?.result?.name) {
    return (
      <Paper className={classes.paper}>
        <Typography variant="h6" align="center">
          Zaloguj się, aby uzyskać więcej funkcji.
        </Typography>
      </Paper>
    );
  }

  return (
    <Paper className={classes.paper}>
      <form autoComplete="off" noValidate className={`${classes.root} ${classes.form}`} onSubmit={handleSubmit}>
        <Typography variant="h6">{currentId ? `Edytuj "${post.title}"` : 'Dodaj post'}</Typography>
        <TextField name="title" variant="outlined" label="Tytuł" fullWidth value={postData.title} onChange={(e) => setPostData({ ...postData, title: e.target.value })} />
        {formErrors.titleError && <Alert severity="error">{formErrors.titleError}</Alert>}
        <TextField name="message" variant="outlined" label="Opis" fullWidth multiline rows={4} value={postData.message} onChange={(e) => setPostData({ ...postData, message: e.target.value })} />
        {formErrors.messageError && <Alert severity="error">{formErrors.messageError}</Alert>}
        <div className={classes.fileInput}><FileBase type="file" multiple={false} onDone={({ base64 }) => setPostData({ ...postData, selectedFile: base64 })} /></div>
        <Button className={classes.buttonSubmit} variant="contained" color="primary" size="large" type="submit" fullWidth>Zatwiedź</Button>
        <Button variant="contained" color="secondary" size="small" onClick={clear} fullWidth>Wyczyść</Button>
      </form>
    </Paper>
  );
};

export default Form;
