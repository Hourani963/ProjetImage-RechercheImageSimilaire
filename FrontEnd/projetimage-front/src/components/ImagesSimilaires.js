import React, {useState, useEffect} from "react"
import axios from 'axios'


const ImagesSimilaires = () =>{
    
    const [imageSimilaire, setImageSimilaire] = useState([]);

    const fetchImagesSimilaires = () =>{
        axios.get('http://localhost:1234/image/download').then(res => {
            setImageSimilaire(res.data);
            console.log(res.data);
        });
    };

    useEffect(() => {
        fetchImagesSimilaires()
        
    }, []);


    return(
        imageSimilaire.map((image, index) =>{
            return(
                <div key={index} className="image" alt={`photo similare ${index}`}>
                    <img src={`http://localhost/1234/image/getimage/${image}`}/>
                </div>
            )
        })
    )
}


export default ImagesSimilaires;