#!/bin/bash

mongoimport --db memeAppDB --collection memesCollection --drop --jsonArray --file ./sample-data-memeApp.json