<template>
  <div ref="canvasContainer" class="game-view"></div>
  <div class="ui-layer">
    <h1>Echo Fishing - Dev Mode</h1>
    <p>FPS: {{ fps }}</p>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import * as THREE from 'three';

const canvasContainer = ref(null);
const fps = ref(0);

onMounted(() => {
  // 1. Khởi tạo Scene (Sân khấu)
  const scene = new THREE.Scene();
  scene.background = new THREE.Color(0x87CEEB); // Màu trời xanh

  // 2. Camera
  const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
  camera.position.set(0, 5, 10);
  camera.lookAt(0, 0, 0);

  // 3. Renderer (Máy chiếu)
  const renderer = new THREE.WebGLRenderer();
  renderer.setSize(window.innerWidth, window.innerHeight);
  canvasContainer.value.appendChild(renderer.domElement);

  // 4. Tạo mặt nước (Tạm thời là một tấm phẳng xanh dương)
  const waterGeometry = new THREE.PlaneGeometry(100, 100);
  const waterMaterial = new THREE.MeshBasicMaterial({ color: 0x0077be, side: THREE.DoubleSide });
  const water = new THREE.Mesh(waterGeometry, waterMaterial);
  water.rotation.x = -Math.PI / 2;
  scene.add(water);

  // 5. Tạo một cái hộp (Đại diện nhân vật)
  const cubeGeo = new THREE.BoxGeometry(1, 2, 1);
  const cubeMat = new THREE.MeshBasicMaterial({ color: 0xff0000 });
  const player = new THREE.Mesh(cubeGeo, cubeMat);
  player.position.y = 1; // Đứng trên mặt nước
  scene.add(player);

  // Loop game
  function animate() {
    requestAnimationFrame(animate);
    player.rotation.y += 0.01; // Xoay nhân vật chơi cho vui
    renderer.render(scene, camera);
  }
  animate();
});
</script>

<style scoped>
.game-view { width: 100vw; height: 100vh; }
.ui-layer { position: absolute; top: 0; left: 0; color: white; padding: 20px; pointer-events: none; }
</style>