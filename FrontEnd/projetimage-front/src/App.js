import './App.css';
import Clock from './components/Clock';
import ImagesSimilaires from './components/ImagesSimilaires';
import ReactUploadImage from './components/ReactUploadImage';

function App() {


  return (
    <div className="App">
      <Clock/>
      <ReactUploadImage/>
      <ImagesSimilaires/>
      
    </div>
  );
}

export default App;
