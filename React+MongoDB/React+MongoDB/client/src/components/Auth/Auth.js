import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Avatar, Button, Paper, Grid, Typography, Container} from '@material-ui/core';
import Alert from '@material-ui/lab/Alert';
import { useHistory } from 'react-router-dom';
import { GoogleLogin } from 'react-google-login';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';

import Icon from './icon';
import { signin, signup } from '../../actions/auth';
import { AUTH } from '../../constants/actionTypes';
import useStyles from './styles';
import Input from './Input';

const initialState = { firstName: '', lastName: '', email: '', password: '', confirmPassword: '' };

const SignUp = () => {
  const [form, setForm] = useState(initialState);
  const [isSignup, setIsSignup] = useState(false);
  const [isError, setIsError] = useState("");
  const dispatch = useDispatch();
  const history = useHistory();
  const classes = useStyles();

  const [showPassword, setShowPassword] = useState(false);
  const handleShowPassword = () => setShowPassword(!showPassword);

  const switchMode = () => {
    setForm(initialState);
    setIsSignup((prevIsSignup) => !prevIsSignup);
    setShowPassword(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const regex_password = /^[\s\S]{4,8}$/;

    if (!regex_password.test(form.password)) {
      setIsError("Hasło musi być długości między 4 a 8 znaków")
    }
    else {
      setIsError('');
    }
    if (!isError) {
      if (isSignup) {
        dispatch(signup(form, history));
      } else {
        dispatch(signin(form, history));
      }
    }
  };

  const googleSuccess = async (res) => {
    const result = res?.profileObj;
    const token = res?.tokenId;

    try {
      dispatch({ type: AUTH, data: { result, token } });

      history.push('/');
    } catch (error) {
      console.log(error);
    }
  };

  const googleError = () => alert('Logowanie za pomocą Google nie jest obecnie dostępne');

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  return (
    <Container component="main" maxWidth="xs">
      <Paper className={classes.paper} elevation={3}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">{isSignup ? 'Zarejestruj' : 'Zaloguj'}</Typography>
        <form className={classes.form} onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            {isSignup && (
              <>
                <Input name="firstName" label="Imię" handleChange={handleChange} autoFocus half />
                <Input name="lastName" label="Nazwisko" handleChange={handleChange} half />
              </>
            )}
            <Input name="email" label="Adres Email" handleChange={handleChange} type="email" />
            <Input name="password" label="Hasło" handleChange={handleChange} type={showPassword ? 'text' : 'password'} handleShowPassword={handleShowPassword} />
            {isError ? <Alert severity="error">{isError}</Alert> : ''}
            {isSignup && <Input name="confirmPassword" label="Powtórz hasło" handleChange={handleChange} type="password" />}
          </Grid>
          <Button type="submit" fullWidth variant="contained" color="primary" className={classes.submit}>
            {isSignup ? 'Zarejestruj' : 'Zaloguj'}
          </Button>
          <GoogleLogin
            clientId=""
            render={(renderProps) => (
              <Button className={classes.googleButton} color="primary" fullWidth onClick={renderProps.onClick} disabled={renderProps.disabled} startIcon={<Icon />} variant="contained">
                Zaloguj przez Google
              </Button>
            )}
            onSuccess={googleSuccess}
            onFailure={googleError}
            cookiePolicy="single_host_origin"
          />
          <Grid container justify="flex-end">
            <Grid item>
              <Button variant="outlined" onClick={switchMode} >
                {isSignup ? 'Masz już konto? Zaloguj się' : "Nie masz konta? Zarejestruj się"}
              </Button>
            </Grid>
          </Grid>
        </form>
      </Paper>
    </Container>
  );
};

export default SignUp;
