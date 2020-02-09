-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-02-2020 a las 21:41:04
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_tienda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `DESCRIPCION` text NOT NULL,
  `PRECIO` int(11) NOT NULL,
  `CATEGORIA` text NOT NULL,
  `RUTA` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`ID`, `NOMBRE`, `DESCRIPCION`, `PRECIO`, `CATEGORIA`, `RUTA`) VALUES
(2, 'Bombillo led', '', 1, 'Ferreteria', 'imagenes\\Ferreteria\\bombilloled1.jpg'),
(1, 'Alicate', '', 5, 'Ferreteria', 'imagenes\\Ferreteria\\alicate1.jpg'),
(3, 'Destornillador', 'destornillador', 4, 'Ferreteria', 'imagenes\\Ferreteria\\destornillador1.jpg'),
(4, 'Martillo', '', 5, 'Ferreteria', 'imagenes\\Ferreteria\\martillobola.jpg'),
(5, 'Tornillo', 'tornillo', 1, 'Ferreteria', 'imagenes\\Ferreteria\\tornillohexagonal.jpg'),
(6, 'Chocolate Carre', 'chocolate', 3, 'Dulces', 'imagenes\\Dulces\\chocolatecarre.jpg'),
(7, 'Chocolate Con Leche', 'ccc', 2, 'Dulces', 'imagenes\\Dulces\\chocolateconleche.png'),
(8, 'Flips', '', 7, 'Dulces', 'imagenes\\Dulces\\flipscaja.jpg'),
(9, 'Galletas Maria Puig', '', 4, 'Dulces', 'imagenes\\Dulces\\galletasmaria.jpg'),
(10, 'Oreo', '', 2, 'Dulces', 'imagenes\\Dulces\\oreo.jpg'),
(11, 'Ron Cacique', '', 10, 'Licores', 'imagenes\\Licores\\cacique.jpg'),
(12, 'Cacique 500', '', 15, 'Licores', 'imagenes\\Licores\\cacique500.jpg'),
(13, 'Old Parr', '', 25, 'Licores', 'imagenes\\Licores\\oldparr12.jpg'),
(14, 'Ron Santa Teresa', '', 11, 'Licores', 'imagenes\\Licores\\santateresa.jpg'),
(15, 'Vodka Bajo Cero', '', 6, 'Licores', 'imagenes\\Licores\\vodkabajoceroazul.jpg'),
(16, 'Coca Cola', '', 3, 'Bebidas', 'imagenes\\Bebidas\\cocacola.jpg'),
(17, 'Coca Cola Lata', '', 1, 'Bebidas', 'imagenes\\Bebidas\\cocacolalata.jpg'),
(18, 'Gatorade Mandarina', '', 1, 'Bebidas', 'imagenes\\Bebidas\\gatorademandarina.jpg'),
(19, 'Jugo Yukery', '', 4, 'Bebidas', 'imagenes\\Bebidas\\jugoyukery.jpg'),
(20, 'Jugo de Pera', '', 3, 'Bebidas', 'imagenes\\Bebidas\\jugodepera.png'),
(21, 'Harina pan', '', 1, 'Alimentos', 'imagenes\\Alimentos\\harinapan.jpg'),
(22, 'Pan Bimbo', '', 2, 'Alimentos', 'imagenes\\Alimentos\\panbimbo.png'),
(23, 'Aceite de Oliva', '', 4, 'Alimentos', 'imagenes\\Alimentos\\aceitedeoliva.jpg'),
(24, 'Mayonesa', '', 5, 'Alimentos', 'imagenes\\Alimentos\\mayonesa.jpg'),
(25, 'Salsa de Tomate', '', 2, 'Alimentos', 'imagenes\\Alimentos\\salsadetomate.jpg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
