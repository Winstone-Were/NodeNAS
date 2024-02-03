const express = require("express");
const cors = require("cors");
const multer = require("multer");

const App = express();
App.use(cors());
App.use(express.json());

const upload = multer({dest:'uploads/'});

App.post("/upload", upload.array("files"),uploadFiles);

function uploadFiles(req,res){
    
}

App.listen(8080,()=>{
    console.log('Server running on port 8080')
})