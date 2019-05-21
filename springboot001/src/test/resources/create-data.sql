INSERT INTO `user` (`user_id`, `email`, `name`) VALUES
(1, 'luis@notengomail.com', 'Luis'),
(2, 'jorge@noleospam.com', 'Jorge'),
(3, 'maria@notengowifi.com', 'María'),
(4, 'luisa@mockmail.com', 'Luisa'),
(5, 'jose@mockmail.com', 'José');

INSERT INTO `article` (`article_id`, `body`, `title`, `user_id`) VALUES
(1, 'Hasta el 40 de mayo...', 'Artículo 1º de Luis', 1),
(2, 'Hasta el 42 de mayo...', 'Artículo 2º de Luis', 1),
(3, 'Hasta el 43 de mayo...', 'Artículo 3º de Luis', 1),
(4, 'Hasta el 44 de mayo...', 'Artículo 4º de Luis', 1),
(5, 'El mundo de Jorge 1', 'Jorge y su mundo 1', 2),
(6, 'El mundo de Jorge 2', 'Jorge y su mundo 2', 2),
(7, 'El mundo de Jorge 3', 'Jorge y su mundo 3', 2),
(8, 'El mundo de Jorge 4', 'Jorge y su mundo 4', 2),
(9, 'Luisa cuida flores blancas', 'Luisa y las flores', 4),
(10, 'María baila los miércoles', 'María va a baile', 3);