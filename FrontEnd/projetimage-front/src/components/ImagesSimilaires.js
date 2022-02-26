import React, {useState, useEffect} from "react"
import axios from 'axios';
import './ImagesSimilaires.css'


class ImagesSimilaires extends React.Component{
    constructor(props){
        super(props);
        this.state= { 
            date: new Date(),
            imageSimilaire :[],
         };
       
      }

      componentDidMount() {
        axios.get('http://localhost:1234/image/download').then(res => {
            const imageSimilaire = res.data;
            this.setState({ imageSimilaire });
        });
        
      };


      render(){
        
        return(
            <div className="container">
                
                {this.state.imageSimilaire.length > 0 ? 
                <>
                {this.state.imageSimilaire.map((image, index) =>{
                    return(
                        
                        <div key={index} className="image" alt={`photo similare ${index}`}>
                            <img src={`http://localhost:1234/image/getimage/${image}`}/>
                        </div>
                    )
                })}
                </>
                :
                null
            }
            </div>
            
            
        )
      }
}

export default ImagesSimilaires;

