<%-- 
    Document   : index
    Created on : 18/08/2015, 10:08:08 AM
    Author     : benavidesa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/libs/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="js/libs/twitter-bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="js/libs/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js"></script>
    <script type="text/javascript" src="js/funciones.js"></script>
    <link rel="stylesheet" href="js/libs/twitter-bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="js/libs/bootstrap-datepicker/css/bootstrap-datepicker.css" />
    <title>JSP Page</title>
  </head>
  <body>
    <div class="container">
      <!--<div class="row"><br><br><br><br></div>-->
      <div class="row">
        <div class="col-lg-3 col-md-3 col-xs-5">
          <form action="server" role="form" method="post">
            <div class="form-group">
              <label for="nombre">Nombre</label>
              <input type="text" class="form-control" name="nombre" id="nombre" />
            </div>
            <div class="form-group">
              <label for="fNacimiento">Fecha de nacimiento</label>
              <input type="text" readonly data-provide="datepicker" name="fNacimiento" class="form-control" id="fNacimiento" />
            </div>
            <div class="form-group">
              <input type="submit"  class="btn btn-success" value="Enviar" />
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>
