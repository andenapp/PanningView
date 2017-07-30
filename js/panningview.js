function PanningView(container, imageSource){

	var lastFrameTime = 0;
	var elapsedTime = 0;
	var duration = 3000;

	var panning;

	var started = false;

	var img;

	var sketch = function(p) {
    	p.setup = function(){
    		p.createCanvas(container.offsetWidth, container.offsetHeight);

      		panning = new Panning();

    		img = p.loadImage(imageSource, function(img) {
    			panning.setSize(p.createVector(img.width, img.height), p.createVector(p.canvas.width, p.canvas.height));
  				p.loop();
  			});

  			p.noLoop();
      	};

    	p.draw = function(){
    		p.background(255);

    		if(started)
				elapsedTime += Date.now() - lastFrameTime;;

			var f = p.constrain(elapsedTime / duration, 0, 1);

    		p.image(img, panning.getX(f), 0);    			

    		if(elapsedTime < duration)
            	lastFrameTime = Date.now();

            
    	};
 	};

	var view = new p5(sketch, container);

	this.start = function(){
		started = true;
		lastFrameTime = Date.now();
		elapsedTime = 0;
	};

	this.setDuration = function(d){
		duration = d;
	};

	this.setPanning = function(p){
		panning = p;
	}
};

function Panning(){

	var displaySize;
	var viewSize;

	var startOffset = 0;
	var endOffset = 0;

	this.setSize = function(d, v){
		displaySize = d;
		viewSize = v;
	};

	this.setStartOffset = function(offset){
		startOffset = offset;
	};

	this.setEndOffset = function(offset){
		endOffset = offset;	
	};

	this.getEndOffset = function(){
		return startOffset + endOffset;
	};

	this.getX = function(delta){
		var start = (viewSize.x * startOffset);
		var end = (viewSize.x - displaySize.x);

		return start + delta * end;
	};

};

  
