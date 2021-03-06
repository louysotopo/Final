<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.List"%>
<% Client mio = (Client)request.getAttribute("ClientObj"); %>
<% String fec = mio.getFecha();String ruci = mio.getRuc();String  nam = mio.getName(); String add =mio.getAddress(); String em = mio.getEmail();  String id= mio.getDocIde(); String  ce = mio.getCelular();%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="../assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>Client Edit</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="../assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="../assets/css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="../assets/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="../assets/css/themify-icons.css" rel="stylesheet">

</head>
<body>

<div class="wrapper">
	<div class="sidebar" data-background-color="white" data-active-color="danger">

    <!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->

    	<div class="sidebar-wrapper">
            <div class="logo">
                <a  class="simple-text">
                    ERP
                </a>
            </div>

            <ul class="nav">

                <li>
                    <a href="/users">
                        <i class="ti-user"></i>
                        <p>Usuarios</p>
                    </a>
                </li>
                <li>
                    <a href="/roles">
                        <i class="ti-stamp"></i>
                        <p>Roles</p>
                    </a>
                </li>
                <li >
                    <a href="/resources">
                        <i class="ti-view-grid"></i>
                        <p>Resources</p>
                    </a>
                </li>
                <li>
                    <a href="/access">
                        <i class="ti-lock"></i>
                        <p>Access</p>
                    </a>
                </li>
                <li>
                    <a href="/products">
                        <i class="ti-view-list-alt"></i>
                        <p>Products</p>
                    </a>
                </li>
                <li >
                    <a href="/providers">
                        <i class="ti-view-list-alt"></i>
                        <p>Providers</p>
                    </a>
                </li>
                <li class="active">
                    <a href="/client">
                        <i class="ti-view-list-alt"></i>
                        <p>Clients</p>
                    </a>
                </li>
                
				<li >
                    <a href="/compras">
                        <i class="ti-view-list-alt"></i>
                        <p>Registro - Compras</p>
                    </a>
                </li>
                <li >
                    <a href="/ventas">
                        <i class="ti-view-list-alt"></i>
                        <p>Registro - Ventas</p>
                    </a>
                </li>
            </ul>
    	</div>
    </div>

    <div class="main-panel">
		<nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    <a class="navbar-brand" href="#">Editar Proveedor</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
						<li>
                            <a href="/users/login">
								<i class="ti-angle-down"></i>
								<p>LogIn</p>
                            </a>
                        </li>
                        <li>
                            <a href="/users/logout">
								<i class="ti-angle-up"></i>
								<p>LogOut</p>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-7">
                        <div class="card">
                            <div class="content">
                                <form action="/client/update" method="get">
                                    
                                    <div class="row">
                                    	<div class="col-md-7">
                                            <div class="form-group">
                                                <label for="">ID</label>
                                                <input type="text" class="form-control border-input" name ="lok" value="<%= mio.getId() %>"  readonly="readonly">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-7">
                                            <div class="form-group">
                                                <label for="">Ultima Fecha de modificacion</label>
                                                <input type="text" class="form-control border-input" name ="aasdd" value="<%= fec %>" >
                                            </div>
                                        </div>
                                    </div>
									<div class="row">
                                        <div class="col-md-7">
                                        	<div class="form-group">
                                                <label >Nombre</label>
                                            	<input type="text" class="form-control border-input" name="name" value ="<%=nam%>"  pattern="[a-zA-Z]{1,40}" title="ingrese mas de una letra y menos de 40" >
      										</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-7">
                                        	<div class="form-group">
                                                <label >Direccion</label>
                                            	<input type="text" class="form-control border-input" name="address" value="<%=add%>"  >
      										</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-7">
                                        	<div class="form-group">
                                                <label >Email</label>
                                            	<input type="email" class="form-control border-input" name="email"  value="<%=em%>" >
      										</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-7">
                                        	<div class="form-group">
                                                <label >RUC</label>
                                            	<input type="text" class="form-control border-input" name="ru"  value="<%=ruci%>"  value="<%=id%>"  pattern="(10|20|17|15|16)[0-9]{9}" title="ruc valido >:v "   >
      										</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-7">
                                        	<div class="form-group">
                                                <label >DNI</label>
                                            	<input type="text" class="form-control border-input" name="ide"  value="<%=id%>" pattern="[0-9]{8}" title="ingrese 8 numeros"  >
      										</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-7">
                                        	<div class="form-group">
                                                <label >Celular</label>
                                            	<input type="text" class="form-control border-input" name="celular"  value="<%=ce%>" pattern="[0-9]{5,15}" title="ingrese mas de 5 numeros" >
      										</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-7">
                                        	<div class="form-group">
                                                <label >Estado</label>
                                            	<select name ="statu">
  													<option value="true">Activo</option>
  													<option value="false">Inactivo</option>
												</select>
      										</div>
                                        </div>
                                    </div>
                                    
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-info btn-fill btn-wd">Guardar</button>
                                    </div>
                                    
                                    
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        

    </div>
</div>


</body>

    <!--   Core JS Files   -->
    <script src="../assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="../assets/js/bootstrap-checkbox-radio.js"></script>

	<!--  Charts Plugin -->
	<script src="../assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="../assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
	<script src="../assets/js/paper-dashboard.js"></script>

	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="../assets/js/demo.js"></script>


</html>
