<template>
  <div ref="canvasContainer" class="game-view"></div>
  <div class="ui-layer">
    <div class="info-box">
      <h2>Echo Fishing Demo</h2>
      <p>Di chuyển: <b>W A S D</b></p>
      <p>Tọa độ: X: {{ Math.round(playerPos.x) }} | Z: {{ Math.round(playerPos.z) }}</p>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, onUnmounted } from 'vue';
import * as THREE from 'three';

const canvasContainer = ref(null);
const playerPos = ref({ x: 0, z: 0 }); 

// Khai báo biến toàn cục
let scene, camera, renderer, player, water;
let keys = { w: false, a: false, s: false, d: false }; 
let animationId;

// Cấu hình Camera góc nhìn thứ 3
const CAMERA_OFFSET = { x: 0, y: 5, z: 8 }; 

onMounted(() => {
  initGame();
  animate();
  window.addEventListener('keydown', handleKeyDown);
  window.addEventListener('keyup', handleKeyUp);
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('keydown', handleKeyDown);
  window.removeEventListener('keyup', handleKeyUp);
  window.removeEventListener('resize', handleResize);
  cancelAnimationFrame(animationId);
});

function initGame() {
  scene = new THREE.Scene();
  scene.background = new THREE.Color(0x87CEEB); 
  scene.fog = new THREE.Fog(0x87CEEB, 10, 50);

  camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);

  renderer = new THREE.WebGLRenderer({ antialias: true });
  renderer.setSize(window.innerWidth, window.innerHeight);
  renderer.shadowMap.enabled = true; 
  canvasContainer.value.appendChild(renderer.domElement);

  // Ánh sáng
  const ambientLight = new THREE.AmbientLight(0xffffff, 0.6); 
  scene.add(ambientLight);

  const dirLight = new THREE.DirectionalLight(0xffffff, 0.8); 
  dirLight.position.set(10, 20, 10);
  dirLight.castShadow = true;
  scene.add(dirLight);

  // Mặt nước
  const waterGeo = new THREE.PlaneGeometry(1000, 1000);
  const waterMat = new THREE.MeshStandardMaterial({ color: 0x0077be, side: THREE.DoubleSide });
  water = new THREE.Mesh(waterGeo, waterMat);
  water.rotation.x = -Math.PI / 2; 
  water.receiveShadow = true;
  scene.add(water);

  // Nhân vật (Hộp đỏ)
  const playerGeo = new THREE.BoxGeometry(1, 2, 1);
  const playerMat = new THREE.MeshStandardMaterial({ color: 0xff4444 });
  player = new THREE.Mesh(playerGeo, playerMat);
  player.position.y = 1; 
  player.castShadow = true;
  scene.add(player);
  
  // Mũi tên hướng mặt
  const directionHelper = new THREE.Mesh(
    new THREE.BoxGeometry(0.2, 0.2, 1), 
    new THREE.MeshBasicMaterial({ color: 0xffff00 })
  );
  directionHelper.position.z = -0.5; 
  directionHelper.position.y = 0.5;
  player.add(directionHelper);
}

function handleKeyDown(e) {
  const key = e.key.toLowerCase();
  if (keys.hasOwnProperty(key)) keys[key] = true;
}

function handleKeyUp(e) {
  const key = e.key.toLowerCase();
  if (keys.hasOwnProperty(key)) keys[key] = false;
}

function updatePlayerMovement() {
  const speed = 0.1;
  const rotateSpeed = 0.05;

  if (keys.w) player.translateZ(-speed); 
  if (keys.s) player.translateZ(speed); 
  if (keys.a) player.rotation.y += rotateSpeed; 
  if (keys.d) player.rotation.y -= rotateSpeed; 

  playerPos.value.x = player.position.x;
  playerPos.value.z = player.position.z;
}

function updateCamera() {
  if (!player) return;
  const relativeOffset = new THREE.Vector3(CAMERA_OFFSET.x, CAMERA_OFFSET.y, CAMERA_OFFSET.z);
  const cameraOffset = relativeOffset.applyMatrix4(player.matrixWorld);
  camera.position.lerp(cameraOffset, 0.1);
  camera.lookAt(player.position.x, player.position.y + 1, player.position.z);
}

function animate() {
  animationId = requestAnimationFrame(animate);
  updatePlayerMovement();
  updateCamera();
  renderer.render(scene, camera);
}

function handleResize() {
  camera.aspect = window.innerWidth / window.innerHeight;
  camera.updateProjectionMatrix();
  renderer.setSize(window.innerWidth, window.innerHeight);
}
</script>

<style scoped>
.game-view { width: 100vw; height: 100vh; display: block; }
.ui-layer { position: absolute; top: 20px; left: 20px; color: white; font-family: sans-serif; user-select: none; }
.info-box { background: rgba(0, 0, 0, 0.5); padding: 15px; border-radius: 8px; border: 1px solid rgba(255, 255, 255, 0.2); }
</style>