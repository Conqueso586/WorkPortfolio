//mouse_util.js 
// helper skeleton controls for mouse movements.

   function degToRad(degrees) {
        return degrees * Math.PI / 180;
    }
   
var mouseDown = false;
  var lastMouseX = null;
  var lastMouseY = null;


  function handleMouseDown(event) {
    mouseDown = true;
    lastMouseX = event.clientX;
    lastMouseY = event.clientY;
  }

  function handleMouseUp(event) {
    mouseDown = false;
    
  }

  function handleMouseMove(event) {
    if (!mouseDown) {
      return;
    }
    var newX = event.clientX;
    var newY = event.clientY;

    var deltaX = newX - lastMouseX;
    
    Tangle = Tangle + deltaX;

    var deltaY = newY - lastMouseY;
    Tspeed = Tspeed - deltaY/1000000.0;
    
    lastMouseX = newX;
    lastMouseY = newY;
    

    tankRotation = mat4.create();
    mat4.identity(tankRotation);

    mat4.rotate(tankRotation, -degToRad(Tangle), upVector);
    mat4.multiplyVec3(tankRotation,Tdirection);
    vec3.normalize(Tdirection);
    }
