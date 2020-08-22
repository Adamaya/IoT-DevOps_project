FROM debian

RUN apt-get update && apt-get upgrade -y

RUN apt-get install python3 python sudo wget -y

RUN  sudo wget http://sourceforge.net/projects/webiopi/files/WebIOPi-0.7.1.tar.gz

RUN sudo tar xvzf WebIOPi-0.7.1.tar.gz

RUN cd WebIOPi-0.7.1 && sudo wget https://raw.githubusercontent.com/doublebind/raspi/master/webiopi-pi2bplus.patch

RUN apt-get install patch python3-pip -y

RUN cd WebIOPi-0.7.1 && sudo patch -p1 -i webiopi-pi2bplus.patch

RUN cd WebIOPi-0.7.1 && sudo ./setup.sh

RUN mkdir webserver && cd webserver && mkdir html && mkdir python

COPY html/ webserver/html/ 

COPY python/ webserver/python/

ADD html/config /etc/webiopi/

EXPOSE 8000
RUN apt-get install vim -y

CMD webiopi -d -c /etc/webiopi/config
