var slideIndex = 1;

// Next/previous controls
function plusSlides(n,x) {
  showSlides(slideIndex += n,x);
}

function showSlides(n,x) {
  var i;
  var slides = document.getElementsByClassName(x);
  if (n > slides.length) {slideIndex = 1;}
  if (n < 1) {slideIndex = slides.length;}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  slides[slideIndex-1].style.display = "block";
}