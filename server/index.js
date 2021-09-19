const express = require('express');
const app = express();
const port = 3000;

var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

//database sqlite3
const sqlite3 = require('sqlite3').verbose();
let db = new sqlite3.Database('./db/data.db');

app.get('/translate/:id', (req, res) => {
    console.log("Accessed api, translate:" + req.params.id)
    var response = { 'en': '', 'def': '', 'error':'none' };
    let query = 'SELECT en FROM translate WHERE hr = "' + req.params.id + '";';
    db.get(query, (err, rows) => {
        if (err) {
            throw err;
        }
        else {
            if (rows === undefined) {
                response['error'] = 'Error: not found!'
                res.json(response);
                res.end();
            }
            else {
                console.log("Succesfully queried : \n", rows, query);
                response['en'] = rows['en'];

                query = 'SELECT en_def FROM definition WHERE en_key = "' + response['en'] + '";';
                db.get(query, (err, rows) => {
                    if (err) {
                        throw err;
                    }
                    else {
                        if (rows === undefined) {
                            res.json({ "error": "notfound" });
                            res.end();
                        }
                        else {
                            console.log("Succesfully queried : \n", rows, query);
                            response['def'] = rows['en_def'];
                            res.json(response);
                        }

                    }
                });
            }

        }
    });
});


app.post('/inputDB', (req, res) => {
    let data = req.body;
    let query = 'INSERT INTO translate(hr, en, de) VALUES(?, ?, ?);';
    db.run(query, [data['hr'], data['en'], data['de']], (err, rows) => {
        if (err) {
            throw err;
        }
        else {
            console.log("Succesfully input : \n", data, query);
        }
    });

    query = 'INSERT INTO definition(en_key, en_def) VALUES(?, ?);';
    db.run(query, [data['en'], data['en_def']], (err, rows) => {
        if (err) {
            throw err;
        }
        else {
            console.log("Succesfully input : \n", data, query);
        }
    });
    res.json({ 'data': 'inserted' });
});



app.listen(port, () => {
    console.log(`App listening on port ${port}!`);
    console.log(`Got it running now!`);
});


