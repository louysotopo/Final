<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.List"%>
<% 
User p = (User)request.getAttribute("user");
List<User> users = (List<User>) request.getAttribute("users");
List<Role> roles=(List<Role>) request.getAttribute("roles");
String [] mes={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Setiembre","Octubre","Noviembre","Diciembre"};
String [] dia={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

String annos="", meses="", dias=""; // Años 
String emailsExist="", rolesExist="";
for( User t : users ) {
	if(t.getUserId().equals(p.getUserId())) {
		String aux="";
	}else{
		emailsExist=emailsExist+"(?!"+t.getEmail()+")";
	}
}
for( Role r : roles ) {
	if(p.getRoleIdName().equalsIgnoreCase(r.getName()))
		rolesExist=rolesExist+"<option value=\""+r.getRoleId()+"\" selected=\"selected\">"+r.getName()+"</option>";
	else
	rolesExist=rolesExist+"<option value=\""+r.getRoleId()+"\">"+r.getName()+"</option>";
}
String date=p.getBirthFormatted();
String a="",m="",d=""; // 02/04/2018
a=date.substring(6);
m=date.substring(3, 5);
d=date.substring(0, 2);

for(int i=1970; i<2018;i++)

	if(a.equalsIgnoreCase(""+i))
		annos="<option value=\""+i+"\" selected=\"selected\">"+i+"</option>"+annos;
	else
		annos="<option value=\""+i+"\">"+i+"</option>"+annos;

for(int j=1; j<=12;j++) {
	if(dia[j-1].equals(m))
		meses="<option value=\""+dia[j-1]+"\" selected=\"selected\">"+mes[j-1]+"</option>"+meses;
	else
	meses="<option value=\""+dia[j-1]+"\">"+mes[j-1]+"</option>"+meses;
}
for(int j=1; j<=12;j++){
	if(d.equals(dia[j-1]))
		dias="<option value=\""+dia[j-1]+"\" selected=\"selected\">"+dia[j-1]+"</option>"+dias;
	else
	dias="<option value=\""+dia[j-1]+"\">"+dia[j-1]+"</option>"+dias;
}
	
%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="../assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>Usuarios Edit</title>

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

                <li class="active">
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
                <li>
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
				<li >
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
                    <a class="navbar-brand" href="#">Editar Usuario</a>
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
                                <form action="/users/edit" method="get">
                                    <div class="row">
                                    	<div class="col-md-7">
                                            <div class="form-group">
                                                <label for="">ID</label>
                                                <input type="email" class="form-control border-input" placeholder="ID" name="id" value="<%=p.getId() %>" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-7">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Rol</label>
                                                <select class="form-control border-input" name="role">
        											<%=rolesExist %>
      											</select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-7">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Email</label>
                                                <input type="email" class="form-control border-input" placeholder="Email" name="email" value="<%=p.getEmail()%>" pattern="<%=emailsExist %>^[^@]+@[^@]+\.[a-zA-Z]{2,}$" title="No podrá ingresar un correo ya registrado."  required>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-7">
                                        	<div class="form-group">
                                                <label >Nacimiento</label>
                                            		<select class="form-control border-input" name="birth[year]" required="required"><%=annos %></select>
        											<select class="form-control border-input" name="birth[month]" required="required"><%=meses %></select>
        											<select class="form-control border-input"name="birth[day]" requ+ired="required"><%=dias %></select>
      										</div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-7">
                                        	<div class="form-group">
                                                <label >Estado</label><br>
                                            	<input type="radio" name="status" value="Activo" checked> Activo<br>
  												<input type="radio" name="status" value="No activo">No activo<br>
      										</div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-7">
                                        	<div class="form-group">
                                        		<label for="exampleInputEmail1">Género</label><br>
                                            	<input type="radio" name="gender" value="Femenino" checked> Femenino<br>
  												<input type="radio" name="gender" value="Masculino">Masculino<br>
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
