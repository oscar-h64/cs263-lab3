gradle build
gradle installDist
docker build ./build/install/listener/ -f Dockerfile -t cs263-lab3-listener:latest
