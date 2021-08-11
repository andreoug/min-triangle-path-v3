#!/bin/bash

echo Start Docker Build
docker build -f Dockerfile -t suprnation:01 .
echo Start Docker Run Test
docker run --rm -ti suprnation:01 bash ./docker_run_test.sh