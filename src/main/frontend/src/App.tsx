import React from 'react';
import {RecoilRoot} from "recoil";
import logo from './assets/logo.svg';
import './styles/App.css';
import Intro from './pages/intro/Intro';

function App() {
  return (
    <RecoilRoot>
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      </header>
      <body>
        <div className={"loginBox"}>
          <Intro/>
        </div>
      </body>
    </RecoilRoot>
  );
}

export default App;
