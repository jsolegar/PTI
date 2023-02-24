/*const express = require('express')
const app = express()
const port = 8080

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.listen(port, () => {
  console.log(`PTI HTTP Server listening at http://localhost:${port}`)
})

app.get('/students/:studentId', function (req, res) {
    res.send('Received request at /students with param studentId='+req.params.studentId)
})

app.get("/students", (req, res, next) => {
    res.json({
        responseId: 1234,
        students: [
            {name: "Jordi", studentId: '12345678a'},
            {name: "Marta", studentId: '12345678b'}
    ]});
});*/

const express = require('express')
const app = express()
const port = 8080

app.use(express.json());

app.post('/newstudent', (req, res, next) => {
    console.log(req.body.name);
    res.end(); 
}) 

app.listen(port, () => {
  console.log(`PTI HTTP Server listening at http://localhost:${port}`)
})
