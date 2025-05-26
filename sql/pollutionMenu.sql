-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业污染源', '1', '1', 'pollution', 'admin/pollution/index', 1, 0, 'C', '0', '0', 'admin:pollution:list', '#', 'admin', current_timestamp, '', null, '企业污染源菜单');

-- 按钮父菜单ID
--SELECT 2059 := LAST_INSERT_ID();
select max(menu_id) from sys_menu;

/*
   全局替换 2059 为 上面语句查询出来的 max(menu_id)
*/

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业污染源查询', 2059, '1',  '#', '', 1, 0, 'F', '0', '0', 'admin:pollution:query',        '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业污染源新增', 2059, '2',  '#', '', 1, 0, 'F', '0', '0', 'admin:pollution:add',          '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业污染源修改', 2059, '3',  '#', '', 1, 0, 'F', '0', '0', 'admin:pollution:edit',         '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业污染源删除', 2059, '4',  '#', '', 1, 0, 'F', '0', '0', 'admin:pollution:remove',       '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业污染源导出', 2059, '5',  '#', '', 1, 0, 'F', '0', '0', 'admin:pollution:export',       '#', 'admin', current_timestamp, '', null, '');