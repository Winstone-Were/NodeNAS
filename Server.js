const express = require("express");
const cors = require("cors");

const App = express();
App.use(cors());

App.listen(8080,()=>{
    console.log('Server running on port 8080')
})