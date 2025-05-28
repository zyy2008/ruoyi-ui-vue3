-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤环境调查监测信息', '1', '1', 'monitoring', 'admin/monitoring/index', 1, 0, 'C', '0', '0', 'admin:monitoring:list', '#', 'admin', current_timestamp, '', null, '土壤环境调查监测信息菜单');

-- 按钮父菜单ID
--SELECT 2090 := LAST_INSERT_ID();
select max(menu_id) from sys_menu;

/*
   全局替换 2090 为 上面语句查询出来的 max(menu_id)
*/

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤环境调查监测信息查询', 2090, '1',  '#', '', 1, 0, 'F', '0', '0', 'admin:monitoring:query',        '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤环境调查监测信息新增', 2090, '2',  '#', '', 1, 0, 'F', '0', '0', 'admin:monitoring:add',          '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤环境调查监测信息修改', 2090, '3',  '#', '', 1, 0, 'F', '0', '0', 'admin:monitoring:edit',         '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤环境调查监测信息删除', 2090, '4',  '#', '', 1, 0, 'F', '0', '0', 'admin:monitoring:remove',       '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('土壤环境调查监测信息导出', 2090, '5',  '#', '', 1, 0, 'F', '0', '0', 'admin:monitoring:export',       '#', 'admin', current_timestamp, '', null, '');