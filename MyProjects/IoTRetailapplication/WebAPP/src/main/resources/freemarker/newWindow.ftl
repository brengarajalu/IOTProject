<!DOCTYPE html>
<html>
<body>

<p>Click the button to open an about:blank page in a new browser window that is 200px wide and 100px tall.</p>

<!--<button onclick="myFunction()">Try it</button>-->
        <!-- <a href="www.mysite.com" onClick="javascript.function();">Item</a>-->
       <!-- <h4 id="map"><a href="/map">Beacon Frequency Report</a></h4>-->
<h4 id="new"><a href="/new" onClick="myFunction()">New</a></h4>
<script>
function myFunction() {
    var myWindow = window.open("heatMap.html", "", "width=200, height=100");
}
</script>

</body>
</html>