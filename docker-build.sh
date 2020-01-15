#!/usr/bin/env bash

docker run -it --rm -p 6990:6990 --stop-timeout 60 --mount type=bind,source="$(pwd)",target=/app --mount source=atlasmvncache,target=/root/.m2/repository sknopp94/atlassdkwithfirefoxselenium package
