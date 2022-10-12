call heroku login

cd .\camera-server
call heroku git:remote -a marzag-cam-dev
call git add . 
call git commit -am "deploy" 
call git push heroku master 

PAUSE