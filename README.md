bash

````

echo-fishing-be/

├── src/

│   ├── main/

│   │   ├── java/

│   │   │   └── com/

│   │   │       └── echommo/

│   │   │           └── fishing/

│   │   │               ├── config/          <-- Cấu hình (WebSocket, Security, CORS)

│   │   │               ├── controller/      <-- API nhận lệnh từ Frontend (VD: Di chuyển, Câu cá)

│   │   │               ├── entity/          <-- Khuôn mẫu dữ liệu (Map, Fish, Player)

│   │   │               ├── repository/      <-- Giao tiếp với Database PostgreSQL

│   │   │               ├── service/         <-- Xử lý Logic game (Tính toán tỷ lệ rớt cá, check hack)

│   │   │               ├── model/           <-- Các object phụ (DTO - Data Transfer Object)

│   │   │               └── FishingApplication.java  <-- File chạy chính

│   │   ├── resources/

│   │   │   ├── static/          <-- Chứa file tĩnh (nếu cần)

│   │   │   ├── application.properties  <-- File cấu hình Server & Database

│   │   │   └── data/            <-- Chứa file JSON dữ liệu game (VD: fish_data.json)

│   │   └── docker/              <-- Chứa file Dockerfile (để deploy sau này)

│   └── test/                    <-- Nơi viết Unit Test (để sau)

└── pom.xml                      <-- Quản lý thư viện (Maven)

````



bash

````

echo-fishing-fe/

├── public/                      <-- Chứa assets không qua xử lý (load trực tiếp)

│   ├── models/                  <-- File 3D (.glb, .gltf) xuất từ Blockbench

│   ├── textures/                <-- Ảnh bề mặt, map

│   └── sounds/                  <-- Âm thanh

├── src/

│   ├── assets/                  <-- CSS, Logo, ảnh tĩnh của UI

│   ├── components/              <-- Các thành phần UI (Thanh máu, Túi đồ, Chat box)

│   │   ├── ui/                  <-- UI 2D đè lên màn hình 3D

│   │   └── game/                <-- Các component game

│   ├── core/                    <-- TRÁI TIM CỦA GAME 3D (Logic Three.js)

│   │   ├── GameScene.js         <-- Quản lý cảnh 3D chính

│   │   ├── InputManager.js      <-- Xử lý phím bấm (WASD, Space)

│   │   ├── Network.js           <-- Xử lý kết nối WebSocket với BE

│   │   └── entities/            <-- Class quản lý đối tượng

│   │       ├── Player.js        <-- Logic nhân vật (di chuyển, animation)

│   │       └── Fish.js          <-- Logic con cá

│   ├── stores/                  <-- Quản lý trạng thái global (Pinia) - Lưu thông tin user đang login

│   ├── views/                   <-- Các màn hình chính (Login, GameWorld)

│   ├── App.vue                  <-- File gốc Vue

│   └── main.js                  <-- File khởi động

├── index.html                   <-- File HTML đầu vào

├── package.json                 <-- Quản lý thư viện (Three.js, Vue, Socket.io)

└── vite.config.js               <-- Cấu hình build

````