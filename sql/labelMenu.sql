-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地图标注', '2006', '1', 'label', 'admin/label/index', 1, 0, 'C', '0', '0', 'admin:label:list', '#', 'admin', current_timestamp, '', null, '地图标注菜单');

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地图标注查询', 2007, '1',  '#', '', 1, 0, 'F', '0', '0', 'admin:label:query',        '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地图标注新增', 2007, '2',  '#', '', 1, 0, 'F', '0', '0', 'admin:label:add',          '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地图标注修改', 2007, '3',  '#', '', 1, 0, 'F', '0', '0', 'admin:label:edit',         '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地图标注删除', 2007, '4',  '#', '', 1, 0, 'F', '0', '0', 'admin:label:remove',       '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地图标注导出', 2007, '5',  '#', '', 1, 0, 'F', '0', '0', 'admin:label:export',       '#', 'admin', current_timestamp, '', null, '');
