import logo from './assets/logo.svg';
import './styles/App.css';
import Intro from './pages/Intro';
import { Link, Route, Routes } from 'react-router-dom';
import callback from './components/callback';

function App() {
  return (
    <>
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      </header>
      <body>
        <div className={"loginBox"}>
          <Routes>
            <Route path='/' element={<Intro/>}/>
            <Route path='/loading' Component={callback}/>
          </Routes>
        </div>
      </body>
    </>
  );
}

export default App;
