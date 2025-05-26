-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业信息', '1', '1', 'enterprise', 'admin/enterprise/index', 1, 0, 'C', '0', '0', 'admin:enterprise:list', '#', 'admin', current_timestamp, '', null, '企业信息菜单');

-- 按钮父菜单ID
--SELECT @parentId := LAST_INSERT_ID();
select max(menu_id) from sys_menu;

/*
   全局替换 @parentId 为 上面语句查询出来的 max(menu_id)
*/

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业信息查询', 2051, '1',  '#', '', 1, 0, 'F', '0', '0', 'admin:enterprise:query',        '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业信息新增', 2051, '2',  '#', '', 1, 0, 'F', '0', '0', 'admin:enterprise:add',          '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业信息修改', 2051, '3',  '#', '', 1, 0, 'F', '0', '0', 'admin:enterprise:edit',         '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业信息删除', 2051, '4',  '#', '', 1, 0, 'F', '0', '0', 'admin:enterprise:remove',       '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('企业信息导出', 2051, '5',  '#', '', 1, 0, 'F', '0', '0', 'admin:enterprise:export',       '#', 'admin', current_timestamp, '', null, '');