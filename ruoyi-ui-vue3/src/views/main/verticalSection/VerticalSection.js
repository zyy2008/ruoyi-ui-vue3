class VerticalSection {
    constructor(canvasId) {
        this.canvas = document.getElementById(canvasId);
        this.ctx = this.canvas.getContext('2d');
        this.clearCanvas();
        this.init();
    }

    init() {
        // 设置画布背景颜色
        this.ctx.fillStyle = 'white';
        this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);

        this.drawBorder();
        // 绘制表格
        this.drawTable();
        this.drawChart();
        this.drawVerticalText();
    }


    clearCanvas() {
        // 清空画布
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.ctx.fillStyle = 'white';
        this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);
    }
    drawBorder() {
        // 绘画布边框
        this.ctx.strokeStyle = 'black';
        this.ctx.strokeRect(0, 0, this.canvas.width, this.canvas.height);
    }
    drawTable() {
        const rows = 5; // 行数
        const cols = 2; // 列数
        const tableWidth = this.canvas.width - 20; // 表格宽度
        const tableHeight = this.canvas.height - 10; // 表格高度

        const cellWidthArrs = [0, 80, tableWidth];
        const cellHeight = 40; // 单元格高度像素
        const margin = 10; // 边距



        // 绘制表格线-横线
        for (let i = 0; i < rows + 1; i++) {
            this.ctx.beginPath();
            this.ctx.moveTo(margin, tableHeight - cellHeight * i);
            this.ctx.lineTo(margin + tableWidth, tableHeight - cellHeight * i);
            this.ctx.stroke();
        }

        // 绘制表格线-竖线
        for (let i = 0; i <= cols + 1; i++) {
            this.ctx.beginPath();
            this.ctx.moveTo(margin + cellWidthArrs[i], tableHeight - cellHeight * 5);
            this.ctx.lineTo(margin + cellWidthArrs[i], tableHeight);
            this.ctx.stroke();
        }

        // 绘制表格内容
        const data = [
            ['地面高程(m)', ''],
            ['管线高程(m)', ''],
            ['规格(mm)', ''],
            ['间距(m)', '37.04'],
            ['总长(m)', '37.04']
        ];

        let textWidths = [55, 90];
        for (let row = rows - 1; row >= 0; row--) {
            for (let col = 0; col < cols; col++) {
                const cell = data[rows - 1 - row][col];
                const x = margin + cellWidthArrs[col] + cellWidthArrs[col + 1] / 2 - textWidths[col] / 2;
                const y = tableHeight - cellHeight * (row + 1) + cellHeight / 2

                this.ctx.fillStyle = 'black';
                this.ctx.fillText(cell, x, y);
            }
        }
    }

    drawChart() {

        const tableWidth = this.canvas.width - 20; // 表格宽度
        const tableHeight = this.canvas.height - 10; // 表格高度

        const cellWidthArrs = [0, 80, tableWidth];
        const cellHeight = 40; // 单元格高度像素
        const margin = 10; // 边距

        this.ctx.beginPath();
        this.ctx.moveTo(margin + 80, margin);
        this.ctx.lineTo(margin + 80, tableHeight - cellHeight * 5);
        this.ctx.stroke();

        const vagueH = 40;
        const maxDepth = 3; //米(最高减去最低的高度差)
        const ratioDepth = (tableHeight - cellHeight * 5 - margin) / maxDepth;
        const scaleW = 15;

        //绘制刻度线
        for (let i = 4; i <= 6; i += 0.1) {
            this.ctx.beginPath();
            this.ctx.moveTo(margin + 80, tableHeight - cellHeight * 5 - vagueH - (i - 4) * ratioDepth);
            this.ctx.lineTo(margin + 80 - scaleW, tableHeight - cellHeight * 5 - vagueH - (i - 4) * ratioDepth);
            this.ctx.stroke();

            //绘制文本
            const text = i.toFixed(1);
            const x = margin + 80 - scaleW - 20;
            const y = tableHeight - cellHeight * 5 - vagueH - (i - 4) * ratioDepth + 4

            this.ctx.fillStyle = 'black';
            this.ctx.fillText(text, x, y);
        }

        // 绘制虚线-起点
        let x1 = margin + 80 + 40;
        let y1 = tableHeight - cellHeight * 1
        this.ctx.beginPath();
        this.ctx.setLineDash([5, 5]); // 设置虚线样式
        // 设置虚线颜色为蓝色
        this.ctx.strokeStyle = "blue";
        this.ctx.moveTo(x1, y1); // 虚线起点
        this.ctx.lineTo(x1, tableHeight - cellHeight * 5 - vagueH - (5 - 4) * ratioDepth); // 虚线终点
        this.ctx.stroke();
        this.ctx.setLineDash([]); // 重置线样式


        //绘制虚线-终点
        let x2 = tableWidth - 40;
        let y2 = tableHeight - cellHeight * 1
        this.ctx.beginPath();
        this.ctx.setLineDash([5, 5]); // 设置虚线样式
        // 设置虚线颜色为蓝色
        this.ctx.strokeStyle = "blue";
        this.ctx.moveTo(x2, y2); // 虚线起点
        this.ctx.lineTo(x2, tableHeight - cellHeight * 5 - vagueH - (4.8 - 4) * ratioDepth); // 虚线终点
        this.ctx.stroke();
        this.ctx.setLineDash([]); // 重置线样式


        //绘制管线
        this.ctx.beginPath();
        this.ctx.moveTo(x1, tableHeight - cellHeight * 5 - vagueH - (5 - 4) * ratioDepth);
        this.ctx.lineTo(x2, tableHeight - cellHeight * 5 - vagueH - (4.8 - 4) * ratioDepth);
        this.ctx.stroke();

        //绘制文本
        const x = x1 - 100;
        const y = tableHeight - cellHeight * 5 - vagueH - (5 - 4) * ratioDepth
        // 设置文字颜色为黄色
        this.ctx.fillStyle = 'blue';
        this.ctx.fillText("管线", x, y + 4);

        this.ctx.fillText("雨水", x1, tableHeight - cellHeight * 5 - vagueH - (5 - 4) * ratioDepth);
        this.ctx.fillText("雨水", x2, tableHeight - cellHeight * 5 - vagueH - (4.8 - 4) * ratioDepth);


        //绘制地面
        this.ctx.beginPath();
        this.ctx.moveTo(x1, tableHeight - cellHeight * 5 - vagueH - (5.3 - 4) * ratioDepth);
        this.ctx.lineTo(x2, tableHeight - cellHeight * 5 - vagueH - (5.5 - 4) * ratioDepth);
        // 设置线条颜色为黄色
        this.ctx.strokeStyle = 'orange';
        this.ctx.stroke();

        this.ctx.fillStyle = 'orange';
        this.ctx.fillText("地面", x, tableHeight - cellHeight * 5 - vagueH - (5.3 - 4) * ratioDepth + 4);



    }

    drawVerticalText() {
        const tableWidth = this.canvas.width - 20; // 表格宽度
        const tableHeight = this.canvas.height - 10; // 表格高度

        const cellWidthArrs = [0, 80, tableWidth];
        const cellHeight = 40; // 单元格高度像素
        const margin = 10; // 边距
        let ctx = this.ctx;

        // 设置字体样式
        ctx.font = '9px Arial';

        let yGG = tableHeight - cellHeight * 2
        ctx.save();
        ctx.translate(120, yGG);
        ctx.rotate(-Math.PI / 2);
        ctx.fillText("600X600", 0, 0);
        ctx.restore();


        let yGX = tableHeight - cellHeight * 3 - 4
        ctx.save();
        ctx.translate(120, yGX);
        ctx.rotate(-Math.PI / 2);
        ctx.fillText("4.930", 0, 0);
        ctx.restore();

        let yDM = tableHeight - cellHeight * 4 - 4
        ctx.save();
        ctx.translate(120, yDM);
        ctx.rotate(-Math.PI / 2);
        ctx.fillText("5.430", 0, 0);
        ctx.restore();



        let yGG2 = tableHeight - cellHeight * 2
        ctx.save();
        ctx.translate(tableWidth - 50, yGG2);
        ctx.rotate(-Math.PI / 2);
        ctx.fillText("600X600", 0, 0);
        ctx.restore();


        let yGX2 = tableHeight - cellHeight * 3 - 4
        ctx.save();
        ctx.translate(tableWidth - 50, yGX2);
        ctx.rotate(-Math.PI / 2);
        ctx.fillText("4.858", 0, 0);
        ctx.restore();

        let yDM2 = tableHeight - cellHeight * 4 - 4
        ctx.save();
        ctx.translate(tableWidth - 50, yDM2);
        ctx.rotate(-Math.PI / 2);
        ctx.fillText("5.538", 0, 0);
        ctx.restore();
    }
}

export default VerticalSection;