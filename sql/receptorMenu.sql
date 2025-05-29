-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块及周边敏感受体信息', '1', '1', 'receptor', 'admin/receptor/index', 1, 0, 'C', '0', '0', 'admin:receptor:list', '#', 'admin', current_timestamp, '', null, '地块及周边敏感受体信息菜单');

-- 按钮父菜单ID
--SELECT 2072 := LAST_INSERT_ID();
select max(menu_id) from sys_menu;

/*
   全局替换 2072 为 上面语句查询出来的 max(menu_id)
*/

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块及周边敏感受体信息查询', 2072, '1',  '#', '', 1, 0, 'F', '0', '0', 'admin:receptor:query',        '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块及周边敏感受体信息新增', 2072, '2',  '#', '', 1, 0, 'F', '0', '0', 'admin:receptor:add',          '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块及周边敏感受体信息修改', 2072, '3',  '#', '', 1, 0, 'F', '0', '0', 'admin:receptor:edit',         '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块及周边敏感受体信息删除', 2072, '4',  '#', '', 1, 0, 'F', '0', '0', 'admin:receptor:remove',       '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块及周边敏感受体信息导出', 2072, '5',  '#', '', 1, 0, 'F', '0', '0', 'admin:receptor:export',       '#', 'admin', current_timestamp, '', null, '');