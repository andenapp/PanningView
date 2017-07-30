function PanningView(container, imageSource){

	var lastFrameTime;
	var elapsedTime;
	var duration = 3000;

	var started = false;

	var img;

	var sketch = function(p) {
    	p.setup = function(){
    		img = p.loadImage(imageSource);
      		p.createCanvas(720, 640);
      		
    	};

    	p.draw = function(){
    		p.background(255);

    		if(started){
				elapsedTime += Date.now() - lastFrameTime;;

				var f = p.constrain(elapsedTime / duration, 0, 1);

				var x = (p.canvas.width) + (f * (p.canvas.width - (img.width + p.canvas.width))); 

    			p.image(img, x, 0);
    			

    			if(elapsedTime < duration)
            		lastFrameTime = Date.now();

            	return;
    		}

    		p.image(img, 0, 0);
    	};
 	};

	this.view = new p5(sketch, container);

	this.start = function(){
		started = true;
		lastFrameTime = Date.now();
		elapsedTime = 0;
	};

	this.setDuration = function(d){
		duration = d;
	};
};

  
