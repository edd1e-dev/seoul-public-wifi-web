document.getElementById("get-location").onclick = () => {
   navigator.geolocation.getCurrentPosition(function(pos) {
      let latitude = pos.coords.latitude;
      let longitude = pos.coords.longitude;
      document.getElementById("lat-input").value = latitude;
      document.getElementById("lnt-input").value = longitude;
   });
};

document.getElementById("get-wifi").onclick = () => {
   let lat = document.getElementById("lat-input").value;
   let lnt = document.getElementById("lnt-input").value;
   window.location.href = "http://localhost:8080/?lat=" + lat + "&lnt=" + lnt;
};

const contents = document.querySelectorAll("#content");

contents.forEach((element, idx) => {
   element.addEventListener('mouseover', () => {
      element.setAttribute("bgcolor", "#DCDCDC");
   });

   element.addEventListener('mouseout', () => {
      element.setAttribute("bgcolor", "#FFFFFF");
   });
});