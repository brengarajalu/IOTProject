
<html>
<head>
  <title>Safeway Retail Shopping</title>
  <style>
  p {
    color: black;
    font-stlye: Sans-serif;
    font-size: 18px;
  }
  #list1 {
    color: black;
    font-stlye: Sans-serif;
    font-size: 18px;
  }
  h4 {
    color: #3366CC;
    font-stlye: Sans-serif;
    font-size: 25px;
  }
  #div1 {
    background-color: #FFCC33;
  }
  .div2 {
    background-color: #FFFFFF;
    font-size: 20px;
  }
  #div3 {
    position: relative;
    -webkit-animation: mymove;
    -webkit-animation-duration: 60s;
    animation: mymove;
    animation-duration: 60s;
    -webkit-animation-iteration-count: 3; /* Chrome, Safari, Opera */
    animation-iteration-count: 3;
  }

  @-webkit-keyframes mymove {
    from {left: 0px;}
    to {right: 700px;}
  }

  @keyframes mymove {
    from {top: 0px;}
    to {top: 200px;}
  }

  </style>
</head>
<body>

<div id="div1">
  <table>
    <tr>
      <td><img src="http://aditirajawat.net/images/logo.png" style="width:250px;height:100px"></td>
      <td style="font-size:18px; color: #00008B"><b>Welcome ${user}!!</b></td>
      <td></td>
  </tr>
  </table>
</div>
<a href="/"><h4><b>Home</b></h4></a>

<div>
  <p>Details of all the places where beacons are installed in the store</p>
  <p> ${details} </p>
</div>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<div id="div3">
  <table>
    <tr>
      <td><img src="http://aditirajawat.net/images/wal11.jpg" style="width:400px;height:400px"></td>
      <td><img src="http://aditirajawat.net/images/wal13.jpg" style="width:400px;height:400px"></td>
      <td><img src="http://aditirajawat.net/images/wal14.jpg" style="width:400px;height:400px"></td>
      <td><img src="http://aditirajawat.net/images/wal7.jpg" style="width:400px;height:400px"></td>
      <td><img src="http://aditirajawat.net/images/wal8.jpg" style="width:400px;height:400px"></td>
      <td><img src="http://aditirajawat.net/images/wal9.jpg" style="width:400px;height:400px"></td>
  </tr>
  </table>
</div>



</body>

</html>
