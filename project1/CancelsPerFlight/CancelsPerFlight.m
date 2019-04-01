
raw = readtable('my_results.txt');
ids = table2array(unique(raw(:,2)))
num = table2array(raw(:,3))

data = zeros(16,28)
indices = []
temp = []
yr = []

for i = 1:size(ids,1)
    temp(:,1) = find(contains(table2array(raw(:,2)),ids{i,1}))
    indices(1:size(temp,1)) = temp(:,1)
    yr = table2array(raw(indices,1))
    yr = yr-2003+1
    data(yr,i) = num(indices,1)
    indices = []
    temp = []
end

rows = {'2003','2004','2005','2006','2007','2008','2009','2010','2011','2012','2013','2014','2015','2016','2017','2018'}
cols = {'Endeavor_Air_Inc','American_Airlines_Inc','Aloha_Airlines_Inc','Alaska_Airlines_Inc','JetBlue_Airways','Continental_Airlines_Inc','Atlantic_Coast_Airlines','Delta_Airlines_Inc','Atlantic_Southeast_Airlines','Frontier_Airlines_Inc','AirTran_Airways','Allegiant_Air','Hawaiian_Airlines_Inc','America_West_Airlines_Inc','Envoy_Air','Spirit_Airlines','Northwest_Airlines_Inc','Comair_Inc','SkyWest_Airlines_Inc','AirBridgeCargo_Airlines','ATA_Airlines','United_Airlines_Inc','US_Airways_Inc','Virgin_America','Southwest_Airlines_Co','ExpressJet_Airlines_Inc','Mesa_Airlines_Inc','Republic_Airline'}
res = array2table(data,'RowNames',rows,'VariableNames',cols)
plot(data)
cdfplot(data)

xlabel('-2\pi < x < 2\pi') 
ylabel('Sine and Cosine Values') 

writetable(res,'CancelsPerFlight.xlsx')