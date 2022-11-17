sudo chmod 777 /home/
sudo mkdir /home/MarzagCam
sudo chmod 777 /home/MarzagCam
sudo mkdir /home/MarzagCam/logs
sudo chmod 777 /home/MarzagCam/logs
sudo mkdir /home/MarzagCam/camera-client
sudo chmod 777 /home/MarzagCam/camera-client
sudo touch /home/MarzagCam/raspberry-manager.config
sudo chmod 777 /home/MarzagCam/raspberry-manager.config
sudo cat > /home/MarzagCam/raspberry-manager.config <<ENDOFFILE
camera.ownerId=1
camera.cameraId=raspberryzero
ENDOFFILE
sudo mkdir /home/MarzagCam/camera-client/logs
sudo chmod 777 /home/MarzagCam/camera-client/logs
sudo mkdir /home/MarzagCam/camera-client/captured-photos
sudo chmod 777 /home/MarzagCam/camera-client/captured-photos
echo initialization completed