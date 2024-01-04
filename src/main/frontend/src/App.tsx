import './styles/App.css';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import { RecoilRoot, useRecoilValue } from 'recoil';
import Header from './components/Header';
import Main from './pages/Main';
import LoginModal from './components/modals/LoginModal';
import SignUpModal from './components/modals/SignUpModal';
import ResetPwdModal from './components/modals/ResetPwdModal';
import SignUpInfoModal from './components/modals/SignUpInfoModal';

function App() {
  return (
    <RecoilRoot>
      <BrowserRouter>
      <Header/>
        <Routes>
            <Route path='/' element={<Main/>}/>
            <Route path='/login' element={<LoginModal/>}/>
            <Route path='/signUp' element={<SignUpModal/>}/>
            <Route path='/resetPwd' element={<ResetPwdModal/>}/>
            <Route path='/signUpInfo' element={<SignUpInfoModal/>}/>
        </Routes>
      </BrowserRouter>
    </RecoilRoot>
  );
}

export default App;
