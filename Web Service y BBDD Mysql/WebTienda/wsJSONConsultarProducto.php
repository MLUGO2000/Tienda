<?PHP
$hostname_localhost="localhost";
$database_localhost="bd_tienda";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["idProducto"])){
		$idProducto=$_GET["idProducto"];
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$consulta="SELECT * FROM productos WHERE ID= '{$idProducto}'";
		$resultado=mysqli_query($conexion,$consulta);
		
	
			
		while($registro=mysqli_fetch_array($resultado)){
			
			$resultar["id"]=$registro['ID'];
			$resultar["nombre"]=$registro['NOMBRE'];
			$resultar["descripcion"]=$registro['DESCRIPCION'];
			$resultar["precio"]=$registro['PRECIO'];
			$resultar["categoria"]=$registro['CATEGORIA'];
			$resultar["ruta"]=$registro['RUTA'];
			$json['producto'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
		
	}
	else{
			$resultar["success"]=0;
			$resultar["message"]='WS No retorna';
			$json['usuario'][]=$resultar;
			echo json_encode($json);
		}
		
		
		
?>