import './App.css';
import ImagesSimilaires from './components/ImagesSimilaires';
import ReactUploadImage from './components/ReactUploadImage';

function App() {


  return (
    <div className="App">
      <ReactUploadImage/>
      <ImagesSimilaires/>
      
    </div>
  );
}

export default App;
