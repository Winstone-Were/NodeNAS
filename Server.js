const express = require("express");
const cors = require("cors");
const multer = require("multer");
const fs = require("fs");

const App = express();
App.use(cors());
App.use(express.json());

const storage = multer.diskStorage({
    destination: (req, file, cb) => {
        cb(null, "uploads")
    },
    filename: (req, file, cb) => {
        cb(null, Date.now()+"-"+file.originalname)
    }
})

const upload = multer({storage});

App.post('/upload', upload.single("file"),(req,res)=>{
    console.log(req.file);
    return res.send("Single File");
});

App.get('/AllFiles', (req,res)=>{
    fs.readdir(__dirname+'/uploads',(err,result)=>{
        if(err){
            console.error(err);
        }

        console.log(result);

        res.send(result);
    })
})

App.get('/',(req,res)=>{
    res.sendFile(__dirname+'/Frontend/index.html');
});

App.listen(8080,()=>{
    console.log('Server running on port 8080')
});