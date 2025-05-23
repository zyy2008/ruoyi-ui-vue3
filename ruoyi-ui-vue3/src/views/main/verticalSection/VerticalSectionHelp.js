
function findMinAndFloor(arr) {
    // 找出数组中的最小值
    if (arr.length === 0) {
        return null; // 如果数组为空，返回null
    }
    let min = arr[0];
    for (let i = 1; i < arr.length; i++) {
        if (arr[i] < min) {
            min = arr[i];
        }
    }

    // 对最小值进行向下取整
    const flooredMin = Math.floor(min);

    return flooredMin;
}

function findMaxAndCeil(arr) {
    // 找出数组中的最大值
    if (arr.length === 0) {
        return null; // 如果数组为空，返回null
    }
    let max = arr[0];
    for (let i = 1; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }

    // 对最大值进行向上取整
    const ceiledMax = Math.ceil(max);

    return ceiledMax;
}



class VerticalSectionHelp {


    constructor(canvasId) {
        this.canvas = document.getElementById(canvasId);
        this.ctx = this.canvas.getContext('2d');
        this.init();
    }

    //初始化画布
    init() {
        // 设置画布背景颜色
        // 开启抗锯齿
        this.ctx.imageSmoothingEnabled = true;
        this.ctx.webkitImageSmoothingEnabled = true;
        this.ctx.fillStyle = "rgba(255, 255, 255, 0.05)";
        this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);
    }
    setOptions(opt) {
        this.space = opt.space; //间距
        this.overall_length = opt.overall_length; //总长
        this.rows = 5;
        this.cols = 2;
        this.margin = 10; // 边距
        this.tableWidth = this.canvas.width - 2 * this.margin; // 表格宽度
        this.tableHeight = this.canvas.height - 1 * this.margin; // 表格高度
        this.cellWidthArrs = [0, 90, this.tableWidth];
        this.cellHeight = 60; // 单元格高度像素
        this.vagueH = 40;//折线图起始高度
        this.maxDepth = 3;//折线图显示的高度差
        this.scaleW = 15;//折线图刻度线宽度
        this.scaleMin = 4;//折线图刻度线最小值
        this.scaleMax = 6;//折线图刻度线最大值
        this.pipelineElevation = opt.pipelineElevation || [4.930, 4.858];//管线高程
        this.groundElevation = opt.groundElevation || [5.430, 5.538];//地面高程
        this.specification = opt.specification || ["600X600", "600X600"];//规格
        this.classifys = opt.classifys;

        this.scaleMin = findMinAndFloor([...this.pipelineElevation, ...this.groundElevation]);//折线图刻度线最小值
        this.scaleMax = findMaxAndCeil([...this.pipelineElevation, ...this.groundElevation]);//折线图刻度线最大值
        this.maxDepth = (this.scaleMax - this.scaleMin) * 1.2
    }
    //清空画布
    clearCanvas() {
        // 清空画布
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.ctx.fillStyle = "rgba(255, 255, 255, 0.05)";
        this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);
    }

    drawAll() {
        this.clearCanvas()
        this.drawBorder()
        this.drawTable()
        this.drawChart()
        this.drawVerticalText()

    }

    drawBorder() {
        // 绘画布边框
        this.ctx.strokeStyle = 'black';
        this.ctx.strokeRect(0, 0, this.canvas.width, this.canvas.height);
    }


    drawTable() {
        const rows = this.rows; // 行数
        const cols = this.cols; // 列数
        const tableWidth = this.tableWidth // 表格宽度
        const tableHeight = this.tableHeight; // 表格高度
        const cellWidthArrs = this.cellWidthArrs;
        const cellHeight = this.cellHeight; // 单元格高度像素
        const margin = this.margin; // 边距



        // 绘制表格线-横线
        for (let i = 0; i < rows + 1; i++) {
            this.ctx.beginPath();
            this.ctx.strokeStyle = "rgba(255, 255, 255, 0.2)";
            this.ctx.moveTo(margin, tableHeight - cellHeight * i);
            this.ctx.lineTo(margin + tableWidth, tableHeight - cellHeight * i);
            this.ctx.stroke();
        }

        // 绘制表格线-竖线
        for (let i = 0; i <= cols + 1; i++) {
            this.ctx.beginPath();
            this.ctx.strokeStyle = "rgba(255, 255, 255, 0.2)";
            if (i === 1) {
                this.ctx.strokeStyle = "rgba(255, 255, 255, 0.5)";
            }
            this.ctx.moveTo(margin + cellWidthArrs[i], tableHeight - cellHeight * rows);
            this.ctx.lineTo(margin + cellWidthArrs[i], tableHeight);
            this.ctx.stroke();
        }

        // 绘制表格内容
        const data = [
            ['地面高程(m)', ''],
            ['管线高程(m)', ''],
            ['规格(mm)', ''],
            ['间距(m)', this.space],
            ['总长(m)', this.overall_length]
        ];

        let textWidths = [55, 90];
        for (let row = rows - 1; row >= 0; row--) {
            for (let col = 0; col < cols; col++) {
                const cell = data[rows - 1 - row][col];
                const x = margin / 2 + cellWidthArrs[col] + cellWidthArrs[col + 1] / 2 - textWidths[col] / 2;
                const y = tableHeight - cellHeight * (row + 1) + cellHeight / 2

                this.ctx.font = '13px Arial';
                this.ctx.fillStyle = '#ffffff';
                this.ctx.fillText(cell, x, y);
            }
        }
    }

    drawChart() {

        const tableWidth = this.tableWidth; // 表格宽度
        const tableHeight = this.tableHeight;; // 表格高度
        const cellHeight = this.cellHeight; // 单元格高度像素
        const margin = this.margin; // 边距
        const cellWidthArrs = this.cellWidthArrs;

        this.ctx.beginPath();
        this.ctx.strokeStyle = "rgba(255, 255, 255, 1)";
        this.ctx.moveTo(margin + cellWidthArrs[1], margin);
        this.ctx.lineTo(margin + cellWidthArrs[1], tableHeight - cellHeight * this.rows);
        this.ctx.stroke();

        const vagueH = this.vagueH;
        const maxDepth = this.maxDepth; //米(最高减去最低的高度差)
        const ratioDepth = (tableHeight - cellHeight * this.rows - margin) / maxDepth;
        const scaleW = this.scaleW;

        //绘制刻度线
        for (let i = this.scaleMin; i <= this.scaleMax; i += 0.1) {
            this.ctx.beginPath();
            this.ctx.strokeStyle = "rgba(255, 255, 255, 0.5)";
            this.ctx.moveTo(margin + cellWidthArrs[1], tableHeight - cellHeight * this.rows - vagueH - (i - this.scaleMin) * ratioDepth);
            this.ctx.lineTo(margin + cellWidthArrs[1] - scaleW, tableHeight - cellHeight * this.rows - vagueH - (i - this.scaleMin) * ratioDepth);
            this.ctx.stroke();

            //绘制文本
            const text = i.toFixed(1);
            const x = margin + cellWidthArrs[1] - scaleW - 20;
            const y = tableHeight - cellHeight * this.rows - vagueH - (i - this.scaleMin) * ratioDepth + this.scaleMin;

            this.ctx.font = '11px Arial';
            this.ctx.fillStyle = '#ffffff';
            this.ctx.fillText(text, x, y);
        }

        // 绘制虚线-起点
        let x1 = margin + 80 + 40;
        let y1 = tableHeight - cellHeight * 1
        this.ctx.beginPath();
        this.ctx.setLineDash([5, 5]); // 设置虚线样式
        // 设置虚线颜色为蓝色
        this.ctx.strokeStyle = "#3DA1FF";
        this.ctx.moveTo(x1, y1); // 虚线起点
        this.ctx.lineTo(x1, tableHeight - cellHeight * this.rows - vagueH - (this.pipelineElevation[0] - this.scaleMin) * ratioDepth); // 虚线终点
        this.ctx.stroke();
        this.ctx.setLineDash([]); // 重置线样式


        //绘制虚线-终点
        let x2 = tableWidth - 40;
        let y2 = tableHeight - cellHeight * 1
        this.ctx.beginPath();
        this.ctx.setLineDash([5, 5]); // 设置虚线样式
        // 设置虚线颜色为黄色
        this.ctx.strokeStyle = "#FF9346";
        this.ctx.moveTo(x2, y2); // 虚线起点
        this.ctx.lineTo(x2, tableHeight - cellHeight * this.rows - vagueH - (this.pipelineElevation[1] - this.scaleMin) * ratioDepth); // 虚线终点
        this.ctx.stroke();
        this.ctx.setLineDash([]); // 重置线样式


        //绘制管线
        this.ctx.beginPath();
        this.ctx.moveTo(x1, tableHeight - cellHeight * this.rows - vagueH - (this.pipelineElevation[0] - this.scaleMin) * ratioDepth);
        this.ctx.lineTo(x2, tableHeight - cellHeight * this.rows - vagueH - (this.pipelineElevation[1] - this.scaleMin) * ratioDepth);
        this.ctx.strokeStyle = '#3DA1FF';
        this.ctx.stroke();

        this.drawSquare(this.ctx, x1, tableHeight - cellHeight * this.rows - vagueH - (this.pipelineElevation[0] - this.scaleMin) * ratioDepth, 8, "#3DA1FF")
        this.drawSquare(this.ctx, x2, tableHeight - cellHeight * this.rows - vagueH - (this.pipelineElevation[1] - this.scaleMin) * ratioDepth, 8, "#3DA1FF")

        //绘制文本
        const x = x1 - 106;
        const y = tableHeight - cellHeight * this.rows - vagueH - (this.pipelineElevation[0] - this.scaleMin) * ratioDepth
        // 设置文字颜色为黄色
        this.ctx.font = '13px Arial';
        this.ctx.fillStyle = '#3DA1FF';
        this.ctx.fillText("管线", x, y + this.scaleMin);

        let offLabel = this.classifys[1].length * 8;
        this.ctx.fillText(this.classifys[0], x1, tableHeight - cellHeight * this.rows - vagueH - (this.pipelineElevation[0] - this.scaleMin) * ratioDepth);
        this.ctx.fillText(this.classifys[1], x2 - offLabel, tableHeight - cellHeight * this.rows - vagueH - (this.pipelineElevation[1] - this.scaleMin) * ratioDepth);


        //绘制地面
        this.ctx.beginPath();
        this.ctx.moveTo(x1, tableHeight - cellHeight * this.rows - vagueH - (this.groundElevation[0] - this.scaleMin) * ratioDepth);
        this.ctx.lineTo(x2, tableHeight - cellHeight * this.rows - vagueH - (this.groundElevation[1] - this.scaleMin) * ratioDepth);
        // 设置线条颜色为黄色
        this.ctx.strokeStyle = '#FF9346';
        this.ctx.stroke();

        this.ctx.fillStyle = '#FF9346';
        this.ctx.fillText("地面", x, tableHeight - cellHeight * this.rows - vagueH - (this.groundElevation[0] - this.scaleMin) * ratioDepth + this.scaleMin);
    }

    drawVerticalText() {
        const tableWidth = this.tableWidth; // 表格宽度
        const tableHeight = this.tableHeight;; // 表格高度
        const cellHeight = this.cellHeight; // 单元格高度像素
        let ctx = this.ctx;

        let yGG = tableHeight - cellHeight * 2 - 2
        ctx.save();
        ctx.translate(120, yGG);
        ctx.rotate(-Math.PI / 2);
        // 设置字体样式
        ctx.font = '12px Arial';
        this.ctx.fillStyle = '#ffffff';
        ctx.fillText(this.specification[0], 0, 0);
        ctx.restore();


        let yGX = tableHeight - cellHeight * 3 - 2
        ctx.save();
        ctx.translate(120, yGX);
        ctx.rotate(-Math.PI / 2);
        // 设置字体样式
        ctx.font = '14px Arial';
        this.ctx.fillStyle = '#3DA1FF';
        ctx.fillText(this.pipelineElevation[0], 0, 0);
        ctx.restore();

        let yDM = tableHeight - cellHeight * 4 - 2
        ctx.save();
        ctx.translate(120, yDM);
        ctx.rotate(-Math.PI / 2);
        // 设置字体样式
        ctx.font = '14px Arial';
        this.ctx.fillStyle = '#FF9346';
        ctx.fillText(this.groundElevation[0], 0, 0);
        ctx.restore();



        let yGG2 = tableHeight - cellHeight * 2 - 2
        ctx.save();
        ctx.translate(tableWidth - 50, yGG2);
        ctx.rotate(-Math.PI / 2);
        // 设置字体样式
        ctx.font = '12px Arial';
        this.ctx.fillStyle = '#ffffff';
        ctx.fillText(this.specification[1], 0, 0);
        ctx.restore();


        let yGX2 = tableHeight - cellHeight * 3 - 2
        ctx.save();
        ctx.translate(tableWidth - 50, yGX2);
        ctx.rotate(-Math.PI / 2);
        // 设置字体样式
        ctx.font = '14px Arial';
        this.ctx.fillStyle = '#3DA1FF';
        ctx.fillText(this.pipelineElevation[1], 0, 0);
        ctx.restore();

        let yDM2 = tableHeight - cellHeight * 4 - 2
        ctx.save();
        ctx.translate(tableWidth - 50, yDM2);
        // 设置字体样式
        ctx.font = '14px Arial';
        this.ctx.fillStyle = '#FF9346';
        ctx.rotate(-Math.PI / 2);
        ctx.fillText(this.groundElevation[1], 0, 0);
        ctx.restore();
    }
    drawSquare(context, centerX, centerY, sideLength, color) {
        // Calculate the top-left corner of the square
        const topLeftX = centerX - (sideLength / 2);
        const topLeftY = centerY - (sideLength / 2);

        // Draw the square
        context.beginPath();
        context.moveTo(topLeftX, topLeftY);
        context.lineTo(topLeftX + sideLength, topLeftY);
        context.lineTo(topLeftX + sideLength, topLeftY + sideLength);
        context.lineTo(topLeftX, topLeftY + sideLength);
        context.closePath();
        context.strokeStyle = color;  // Set the stroke color
        context.stroke();
    }

    drawCircle(context, centerX, centerY, radius, color) {
        // Draw the circle
        context.beginPath();
        context.arc(centerX, centerY, radius, 0, 2 * Math.PI);
        context.strokeStyle = color;  // Set the stroke color
        context.stroke();
    }
}


export default VerticalSectionHelp


