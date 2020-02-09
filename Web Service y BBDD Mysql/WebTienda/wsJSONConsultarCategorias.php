<?PHP
$hostname_localhost="localhost";
$database_localhost="bd_tienda";
$username_localhost="root";
$password_localhost="";

$json=array();

		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$consulta="SELECT * FROM categorias ";
		$resultado=mysqli_query($conexion,$consulta);
		
	
			
		while($registro=mysqli_fetch_array($resultado)){
			
			$resultar["nombre"]=$registro['nombre'];
			$json['categorias'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
		
	
	
		
		
		
		
		
		/*$resultar["id"]=0;
			$resultar["nombre"]='No Registra';
			$resultar["descripcion"]='No Registra';
			$resultar["precio"]=0;
			$resultar["categoria"]='No Registra';
			$resultar["ruta"]='No Registra';
			$json['usuario'][]=$resultar;*/
?>