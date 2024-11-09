using System;
using System.Net.Http;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using Frontend.Models;
using Frontend.Services;

namespace Frontend
{
    public partial class MainWindow : Window
    {
        private readonly ResumeService _resumeService;

        public MainWindow()
        {
            InitializeComponent();
            _resumeService = new ResumeService(App.HttpClient);
        }

        private async void SubmitButton_Click(object sender, RoutedEventArgs e)
        {
            var query = new ResumeQuery
            {
                Sex = SexBox.Text == "Sex" ? null : SexBox.Text,
                Experience = ExperienceBox.Text == "Experience" ? null : ExperienceBox.Text,
                Skills = SkillsBox.Text == "Skills (comma-separated)" ? null : SkillsBox.Text.Split(',').Select(s => s.Trim()).ToList()
            };

            try
            {
                var resumes = await _resumeService.GetResumesAsync(query);

                var resumeListWindow = new ResumeListWindow(resumes);
                resumeListWindow.Show();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Ошибка при поиске резюме: {ex.Message}");
            }
        }

        private void TextBox_GotFocus(object sender, RoutedEventArgs e)
        {
            var textBox = sender as TextBox;
            if (textBox != null && textBox.Foreground == Brushes.Gray)
            {
                textBox.Text = string.Empty;
                textBox.Foreground = Brushes.Black;
            }
        }

        private void TextBox_LostFocus(object sender, RoutedEventArgs e)
        {
            var textBox = sender as TextBox;
            if (textBox != null && string.IsNullOrWhiteSpace(textBox.Text))
            {
                textBox.Foreground = Brushes.Gray;
                textBox.Text = textBox.Name switch
                {
                    "SexBox" => "Sex",
                    "ExperienceBox" => "Experience",
                    "SkillsBox" => "Skills (comma-separated)",
                    _ => textBox.Text
                };
            }
        }
    }
}
