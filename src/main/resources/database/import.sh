#!/bin/bash

mongoimport --db promotions --collection products --drop --file /docker-entrypoint-initdb.d/01-products.json --jsonArray