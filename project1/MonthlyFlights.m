raw = readtable('monthly flights.txt');
sorted = sort(table2array(raw),1);
sample_mean = mean(sorted(:,2))
sample_std = std(sorted(:,2))
t_stat = zeros(12,1)
for i=1:12
    t_stat(i,1) = (sorted(i,2)-sample_mean)/(sample_std/sqrt(12))
end
months = {'Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'};
t_stat = array2table(t_stat,"RowNames",months);
monthlydelays = array2table(sorted,"RowNames",months);
writetable(t_stat,'t_stats.xlsx')
writetable(monthlydelays,'MonthlyDelays.xlsx')