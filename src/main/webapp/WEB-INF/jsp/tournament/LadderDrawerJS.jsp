<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../../../resources/lib/fabric.min.js"></script>
<head>
    <script>
        function LadderDrawer(tournament) {
            this.stages = tournament.stages;
            this.canvas = new fabric.Canvas('ladder');
            this.x = 30;
            this.y = 30;
            this.lineWidth = 4;

            this.draw = function () {
                var phase = this.getMinPhase(this.stages);
                var maxPlayerName = this.getMaxPlayerNameLength();
                var stagesBoxes = [];
                var stageLineBoxes = [];
                var globalCounter = -1;
                while (true) {
                    var pairBoxes = [];
                    var counter = 0;
                    for (var i = 0; i < this.stages.length; i++) {
                        if (this.stages[i].phaseCode == phase) {
                            if (stageLineBoxes.length == 0) {
                                var pairBox = this.getPairBox(this.stages[i], this.x, this.y, maxPlayerName);
                            } else {
                                var lineBox = stageLineBoxes[globalCounter][counter];
                                var pairBox = this.getPairBox(this.stages[i], lineBox.left + lineBox.width, lineBox.top + (lineBox.height / 2), maxPlayerName);
                                counter++;
                            }
                            pairBoxes.push(pairBox);
                            this.canvas.add(pairBox);
                            this.y = this.y + 75;
                        }
                    }
                    var linesBoxes = [];
                    for (var i = 0; i < pairBoxes.length; i = i + 2) {
                        var topPairBox = pairBoxes[i];
                        var bottomPairBox = pairBoxes[i + 1];
                        var topX = topPairBox.left + topPairBox.width;
                        var topY = topPairBox.top + (topPairBox.height / 2);
                        var bottomX = bottomPairBox.left + bottomPairBox.width;
                        var bottomY = bottomPairBox.top + (bottomPairBox.height / 2);

                        var lineGroup = this.drawConnectingLines(topX, topY, bottomX, bottomY);
                        linesBoxes.push(lineGroup);
                        this.canvas.add(lineGroup);
                    }
                    stagesBoxes.push(pairBox);
                    stageLineBoxes.push(linesBoxes);
                    if (phase == 1) {
                        break;
                    }
                    phase = phase / 2;
                    globalCounter++;
                }

                return stagesBoxes;

            }


            this.getPairBox = function (stage, left, top, maxLenght) {
                this.firstPlayerName = '';
                if (typeof stage.firstPlayer !== 'undefined') {
                    this.firstPlayerName = stage.firstPlayer.name + ' ' + stage.firstPlayer.surname;
                }

                this.secondPlayerName = '';
                if (typeof stage.secondPlayer !== 'undefined') {
                    this.secondPlayerName = stage.secondPlayer.name + ' ' + stage.secondPlayer.surname;
                }
                var firstPlayer = new fabric.Text(this.firstPlayerName, {
                    originX: 'center',
                    originY: 'center',
                    fontSize: 10
                });
                var secondPlayer = new fabric.Text(this.secondPlayerName, {
                    originX: 'center',
                    originY: 'center',
                    fontSize: 10
                });
                var border = new fabric.Rect({
                    width: maxLenght * 10,
                    height: secondPlayer.height * 2,
                    stroke: 'black',
                    strokeWidth: 3,
                    fill: 'white',
                    originX: 'center',
                    originY: 'center'
                });
                var firstPartGroup = new fabric.Group([border, firstPlayer], {
                    left: left,
                    top: top - firstPlayer.height * 2
                });
                var secondPartGroup = new fabric.Group([border, secondPlayer], {
                    left: left,
                    top: top
                });

                var summaryGroup = new fabric.Group([firstPartGroup, secondPartGroup], {selectable: false});
                summaryGroup.on('mousedown', function (e,firstPlayerName,secondPlayerName) {
                    $('#myModal').modal('show');
                    $('#firstPlayer').text(this._objects[0]._objects[1].__text);
                    $('#secondPlayer').text(this._objects[1]._objects[1].__text);
                });

                return summaryGroup;
            }

            this.getMinPhase = function (stages) {
                var minPhase = 1;
                for (var i = 0; i < stages.length; i++) {
                    if (stages[i].phaseCode > minPhase) {
                        minPhase = stages[i].phaseCode;
                    }
                }
                return minPhase;
            }

            this.drawConnectingLines = function (topX, topY, bottomX, bottomY) {

                var topLine = new fabric.Line([topX, topY - this.lineWidth / 2, topX + 100 + this.lineWidth, topY - this.lineWidth / 2], {
                    stroke: 'black',
                    strokeWidth: this.lineWidth
                });
                var bottomLine = new fabric.Line([bottomX, bottomY - this.lineWidth / 2, bottomX + 100 + this.lineWidth, bottomY - this.lineWidth / 2], {
                    stroke: 'black',
                    strokeWidth: this.lineWidth
                });
                var verticalLine = new fabric.Line([topX + 100, topY, bottomX + 100, bottomY], {
                    stroke: 'black',
                    strokeWidth: this.lineWidth
                });
                var shortHorizontalLine = new fabric.Line([topX + 100, (topY + verticalLine.height / 2), topX + 200, (topY + verticalLine.height / 2)], {
                    stroke: 'black',
                    strokeWidth: this.lineWidth
                });
                var group = new fabric.Group([topLine, bottomLine, verticalLine, shortHorizontalLine], {
                    selectable: false,
                    evented: false
                });
                return group;
            }

            this.getMaxPlayerNameLength = function () {
                var maxLength = this.getLength(this.stages[0].firstPlayer);
                if (this.getLength(this.stages[0].secondPlayer) > maxLength) {
                    maxLength = this.getLength(this.stages[0].secondPlayer);
                }

                for (var i = 1; i < this.stages.length; i++) {
                    if (this.getLength(this.stages[i].firstPlayer) > maxLength) {
                        maxLength = this.getLength(this.stages[i].firstPlayer);
                    }
                    if (this.getLength(this.stages[i].secondPlayer) > maxLength) {
                        maxLength = this.getLength(this.stages[i].secondPlayer);
                    }
                }
                return maxLength;
            }

            this.getLength = function (player) {
                if (typeof player !== 'undefined') {
                    var fullName = player.name + ' ' + player.surname;
                    return fullName.length;
                } else {
                    return 0;
                }

            }

        }
    </script>
</head>