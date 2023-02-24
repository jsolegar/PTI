const express = require('express')
const fs = require('fs')
const port = 8080
const app = express()

const data_path = './rentals.json';

app.use(express.json());

app.get('/',(req,res) => {
	res.status(201).end();
	console.log('FUNCIONANDO');
});


app.get('/list', (req, res) => {
    res.send(JSON.parse(fs.readFileSync(data_path)));
});

app.post('/new', (req, res) => {

    let rental = {
        maker: req.body.maker,
        model: req.body.model,
        days:  req.body.days,
        units: req.body.units
    }; 

    let rentals = JSON.parse(fs.readFileSync(data_path));
    console.log(cars);
    rentals.push(rental);
    console.log(cars);

    fs.writeFileSync(data_path, JSON.stringify(rentals), (err) => {
        if (err) return console.log(err);
        console.log('added new car');
    });
    res.status(201);
    res.send(rental);
    res.end();
});

app.listen(port, () => {
    if (!fs.existsSync(data_path)) {
        fs.appendFile(data_path, JSON.stringify({car: []}), (err) => {
            if (err) return console.log(err);
        });
    }
    console.log(`Listening: http://localhost:${port}`);
});
