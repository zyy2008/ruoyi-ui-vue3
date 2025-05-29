-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('园区初调监测井信息，记录每个监测井的基础属性与附加信息', '1', '1', 'wells', 'admin/wells/index', 1, 0, 'C', '0', '0', 'admin:wells:list', '#', 'admin', current_timestamp, '', null, '园区初调监测井信息，记录每个监测井的基础属性与附加信息菜单');

-- 按钮父菜单ID
--SELECT 2066 := LAST_INSERT_ID();
select max(menu_id) from sys_menu;

/*
   全局替换 2066 为 上面语句查询出来的 max(menu_id)
*/

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('园区初调监测井信息，记录每个监测井的基础属性与附加信息查询', 2066, '1',  '#', '', 1, 0, 'F', '0', '0', 'admin:wells:query',        '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('园区初调监测井信息，记录每个监测井的基础属性与附加信息新增', 2066, '2',  '#', '', 1, 0, 'F', '0', '0', 'admin:wells:add',          '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('园区初调监测井信息，记录每个监测井的基础属性与附加信息修改', 2066, '3',  '#', '', 1, 0, 'F', '0', '0', 'admin:wells:edit',         '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('园区初调监测井信息，记录每个监测井的基础属性与附加信息删除', 2066, '4',  '#', '', 1, 0, 'F', '0', '0', 'admin:wells:remove',       '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('园区初调监测井信息，记录每个监测井的基础属性与附加信息导出', 2066, '5',  '#', '', 1, 0, 'F', '0', '0', 'admin:wells:export',       '#', 'admin', current_timestamp, '', null, '');
