var panningView;

function setup() {
	panningView = new PanningView(720, 640, "images/subway.png");
	panningView.start();
}

function draw() {
	panningView.update();
  	panningView.draw();
}