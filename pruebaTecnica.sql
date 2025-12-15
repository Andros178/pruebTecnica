--
-- PostgreSQL database dump
--

\restrict E5H7Lf0djjhso6GKsr356377Qp1cwOozSI37xSp8a3i79Pst84xbf8aoVCMoU2g

-- Dumped from database version 16.11 (Ubuntu 16.11-1.pgdg24.04+1)
-- Dumped by pg_dump version 18.1 (Ubuntu 18.1-1.pgdg24.04+2)

-- Started on 2025-12-15 13:11:38 -05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3614 (class 0 OID 31490)
-- Dependencies: 232
-- Data for Name: archivo_adjunto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3616 (class 0 OID 39458)
-- Dependencies: 234
-- Data for Name: comentario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3620 (class 0 OID 39501)
-- Dependencies: 238
-- Data for Name: documento_tipo_tramite; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.documento_tipo_tramite (id, tipo_tramite_id, tipo_documento_id, estado_id) VALUES (1, 2, 2, 1);
INSERT INTO public.documento_tipo_tramite (id, tipo_tramite_id, tipo_documento_id, estado_id) VALUES (2, 2, 3, 1);
INSERT INTO public.documento_tipo_tramite (id, tipo_tramite_id, tipo_documento_id, estado_id) VALUES (3, 3, 2, 1);
INSERT INTO public.documento_tipo_tramite (id, tipo_tramite_id, tipo_documento_id, estado_id) VALUES (4, 3, 4, 1);


--
-- TOC entry 3600 (class 0 OID 31368)
-- Dependencies: 218
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estado (id, nombre) VALUES (3, 'FINALIZADO');
INSERT INTO public.estado (id, nombre) VALUES (4, 'RECHAZADO');
INSERT INTO public.estado (id, nombre) VALUES (1, 'ACTIVO');
INSERT INTO public.estado (id, nombre) VALUES (2, 'INACTIVO');
INSERT INTO public.estado (id, nombre) VALUES (5, 'RADICADO');
INSERT INTO public.estado (id, nombre) VALUES (6, 'EN_PROCESO');


--
-- TOC entry 3604 (class 0 OID 31396)
-- Dependencies: 222
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.persona (id, nombre, segundo_nombre, apellido, segundo_apellido, correo, numero_identificacion, tipo_identificacion_id, estado_id) VALUES (1, 'Sergio', 'Andres', 'Martinez', 'Velasquez', 'u20211195550@usco.edu.co', '1006128793', 1, 1);


--
-- TOC entry 3602 (class 0 OID 31377)
-- Dependencies: 220
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.rol (id, nombre, estado_id) VALUES (1, 'ESTUDIANTE', 1);
INSERT INTO public.rol (id, nombre, estado_id) VALUES (3, 'ADMINISTRATIVO', 1);
INSERT INTO public.rol (id, nombre, estado_id) VALUES (4, 'FUNCIONARIO', 1);
INSERT INTO public.rol (id, nombre, estado_id) VALUES (2, 'DOCENTE', 1);


--
-- TOC entry 3621 (class 0 OID 39531)
-- Dependencies: 239
-- Data for Name: seguimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3618 (class 0 OID 39483)
-- Dependencies: 236
-- Data for Name: tipo_documento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_documento (id, nombre, estado_id) VALUES (1, 'Comprobante de pago', 1);
INSERT INTO public.tipo_documento (id, nombre, estado_id) VALUES (2, 'Documento de identidad', 1);
INSERT INTO public.tipo_documento (id, nombre, estado_id) VALUES (3, 'Certificado academico', 1);
INSERT INTO public.tipo_documento (id, nombre, estado_id) VALUES (4, 'Formulario de cancelación', 1);


--
-- TOC entry 3606 (class 0 OID 31413)
-- Dependencies: 224
-- Data for Name: tipo_identificacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_identificacion (id, nombre, estado_id) VALUES (2, 'Tarjeta de Identidad', 1);
INSERT INTO public.tipo_identificacion (id, nombre, estado_id) VALUES (1, 'Cedula', 1);
INSERT INTO public.tipo_identificacion (id, nombre, estado_id) VALUES (3, 'Cedula de extranjeria', 1);


--
-- TOC entry 3610 (class 0 OID 31454)
-- Dependencies: 228
-- Data for Name: tipo_tramite; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_tramite (id, nombre, estado_id, descripcion) VALUES (1, 'Queja', 1, NULL);
INSERT INTO public.tipo_tramite (id, nombre, estado_id, descripcion) VALUES (2, 'Homologacion', 1, 'Este proceso se realiza para validar creditos y asignaturas');
INSERT INTO public.tipo_tramite (id, nombre, estado_id, descripcion) VALUES (3, 'Cancelación de semestre', 1, 'Este proceso de realiza para desistir de ver clases en el presente semestre');
INSERT INTO public.tipo_tramite (id, nombre, estado_id, descripcion) VALUES (4, 'Reclamo', 1, NULL);


--
-- TOC entry 3612 (class 0 OID 31471)
-- Dependencies: 230
-- Data for Name: tramite; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3598 (class 0 OID 31359)
-- Dependencies: 216
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario (id, contrasena, persona_id, estado_id, nombre) VALUES (15, '$2a$10$/eUAwbBu7vhc.fRBCz6xWOp9QeDNl4BX8/J4n6E3cpg/dXbUtJ6yi', 1, 1, 'adminUser');


--
-- TOC entry 3608 (class 0 OID 31432)
-- Dependencies: 226
-- Data for Name: usuario_rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario_rol (id, usuario_id, rol_id, estado_id) VALUES (9, 15, 3, 1);


--
-- TOC entry 3641 (class 0 OID 0)
-- Dependencies: 231
-- Name: archivo_adjunto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.archivo_adjunto_id_seq', 10, true);


--
-- TOC entry 3642 (class 0 OID 0)
-- Dependencies: 233
-- Name: comentario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comentario_id_seq', 3, true);


--
-- TOC entry 3643 (class 0 OID 0)
-- Dependencies: 237
-- Name: documento_tipo_tramite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.documento_tipo_tramite_id_seq', 4, true);


--
-- TOC entry 3644 (class 0 OID 0)
-- Dependencies: 217
-- Name: estado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_id_seq', 6, true);


--
-- TOC entry 3645 (class 0 OID 0)
-- Dependencies: 221
-- Name: persona_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.persona_id_seq', 24, true);


--
-- TOC entry 3646 (class 0 OID 0)
-- Dependencies: 219
-- Name: rol_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rol_id_seq', 4, true);


--
-- TOC entry 3647 (class 0 OID 0)
-- Dependencies: 240
-- Name: seguimiento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seguimiento_id_seq', 4, true);


--
-- TOC entry 3648 (class 0 OID 0)
-- Dependencies: 235
-- Name: tipo_documento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_documento_id_seq', 4, true);


--
-- TOC entry 3649 (class 0 OID 0)
-- Dependencies: 223
-- Name: tipo_identificacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_identificacion_id_seq', 3, true);


--
-- TOC entry 3650 (class 0 OID 0)
-- Dependencies: 227
-- Name: tipo_tramite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_tramite_id_seq', 4, true);


--
-- TOC entry 3651 (class 0 OID 0)
-- Dependencies: 229
-- Name: tramite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tramite_id_seq', 6, true);


--
-- TOC entry 3652 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 15, true);


--
-- TOC entry 3653 (class 0 OID 0)
-- Dependencies: 225
-- Name: usuario_rol_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_rol_id_seq', 9, true);


-- Completed on 2025-12-15 13:11:38 -05

--
-- PostgreSQL database dump complete
--

\unrestrict E5H7Lf0djjhso6GKsr356377Qp1cwOozSI37xSp8a3i79Pst84xbf8aoVCMoU2g

