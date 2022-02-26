import React, {useState, useEffect} from "react"
import axios from 'axios'


class ImagesSimilaires extends React.Component{
    state = {
        imageSimilaire: []
        
      }

      componentDidMount() {
        axios.get('http://localhost:1234/image/download').then(res => {
            const imageSimilaire = res.data;
            this.setState({ imageSimilaire });
        });
      };

      render(){
        return(
            this.state.imageSimilaire.map((image, index) =>{
                
                return(
                    <div key={index} className="image" alt={`photo similare ${index}`}>
                        <img src={`http://localhost:1234/image/getimage/${image}`}/>
                    </div>
                )
            })
        )
      }
}

export default ImagesSimilaires;

