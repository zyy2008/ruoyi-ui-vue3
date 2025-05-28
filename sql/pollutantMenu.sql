-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤监测发现的超标污染物信息', '1', '1', 'pollutant', 'admin/pollutant/index', 1, 0, 'C', '0', '0', 'admin:pollutant:list', '#', 'admin', current_timestamp, '', null, '土壤监测发现的超标污染物信息菜单');

-- 按钮父菜单ID
--SELECT 2078 := LAST_INSERT_ID();
select max(menu_id) from sys_menu;

/*
   全局替换 2078 为 上面语句查询出来的 max(menu_id)
*/

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤监测发现的超标污染物信息查询', 2078, '1',  '#', '', 1, 0, 'F', '0', '0', 'admin:pollutant:query',        '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤监测发现的超标污染物信息新增', 2078, '2',  '#', '', 1, 0, 'F', '0', '0', 'admin:pollutant:add',          '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤监测发现的超标污染物信息修改', 2078, '3',  '#', '', 1, 0, 'F', '0', '0', 'admin:pollutant:edit',         '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤监测发现的超标污染物信息删除', 2078, '4',  '#', '', 1, 0, 'F', '0', '0', 'admin:pollutant:remove',       '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤监测发现的超标污染物信息导出', 2078, '5',  '#', '', 1, 0, 'F', '0', '0', 'admin:pollutant:export',       '#', 'admin', current_timestamp, '', null, '');